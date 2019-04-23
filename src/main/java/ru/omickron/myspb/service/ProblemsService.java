package ru.omickron.myspb.service;

import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.omickron.myspb.Api;
import ru.omickron.myspb.exception.NoBodyInReasonGroupException;
import ru.omickron.myspb.exception.NoReasonInReasonGroupException;
import ru.omickron.myspb.exception.ReasonGroupNotFoundException;
import ru.omickron.myspb.exception.UserNotFoundException;
import ru.omickron.myspb.model.ReasonGroup;
import ru.omickron.myspb.model.User;
import ru.omickron.myspb.service.dto.CreatedProblemResponse;
import ru.omickron.myspb.service.dto.ProblemResponse;
import ru.omickron.myspb.service.dto.ProblemsPageResponse;
import ru.omickron.myspb.service.dto.ShortProblemResponse;
import ru.omickron.myspb.service.dto.Token;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ProblemsService {
    @NonNull
    private final UserService userService;
    @NonNull
    private final ReasonGroupService reasonGroupService;

    @NonNull
    public List<ShortProblemResponse> getProblems( @NonNull Token token ) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth( token.getAccessToken() );
        RequestEntity<String> requestEntity =
                new RequestEntity<>( headers, HttpMethod.GET, URI.create( Api.MY_PROBLEMS ) );
        return restTemplate.exchange( requestEntity, ProblemsPageResponse.class ).getBody().getResults();
    }

    @NonNull
    public ProblemResponse getProblem( @NonNull Token token, @NonNull Long id ) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth( token.getAccessToken() );
        RequestEntity<String> requestEntity =
                new RequestEntity<>( headers, HttpMethod.GET, URI.create( Api.PROBLEMS + id ) );
        return restTemplate.exchange( requestEntity, ProblemResponse.class ).getBody();
    }

    @NonNull
    public ProblemResponse createProblem( @NonNull Token token, @NonNull Long reasonGroupId, @NonNull Double latitude,
            @NonNull Double longitude, @NonNull MultipartFile[] files ) {
        User user = userService.findUserByToken( token.getAccessToken() ).orElseThrow( UserNotFoundException :: new );
        ReasonGroup reasonGroup = reasonGroupService.findByUserAndId( user, reasonGroupId )
                .orElseThrow( ReasonGroupNotFoundException :: new );
        if (isNull( reasonGroup.getBody() )) {
            throw new NoBodyInReasonGroupException();
        }
        if (isNull( reasonGroup.getReasonId() )) {
            throw new NoReasonInReasonGroupException();
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth( token.getAccessToken() );
        headers.setContentType( MediaType.MULTIPART_FORM_DATA );
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add( "body", reasonGroup.getBody() );
        map.add( "latitude", latitude );
        map.add( "longitude", longitude );
        map.add( "reason", reasonGroup.getReasonId() );
        for (MultipartFile file : files) {
            map.add( "files", file.getResource() );
        }

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>( map, headers );
        CreatedProblemResponse createdProblemResponse =
                restTemplate.postForEntity( URI.create( Api.PROBLEMS ), httpEntity, CreatedProblemResponse.class )
                        .getBody();
        return getProblem( token, createdProblemResponse.getId() );
    }
}
