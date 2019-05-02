package ru.omickron.myspb.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import ru.omickron.myspb.Api;
import ru.omickron.myspb.exception.NoBodyInReasonGroupException;
import ru.omickron.myspb.exception.NoReasonInReasonGroupException;
import ru.omickron.myspb.exception.ReasonGroupNotFoundException;
import ru.omickron.myspb.exception.UserNotFoundException;
import ru.omickron.myspb.interceptor.RequestContext;
import ru.omickron.myspb.model.ReasonGroup;
import ru.omickron.myspb.model.User;
import ru.omickron.myspb.service.dto.CreatedProblemResponse;
import ru.omickron.myspb.service.dto.ProblemResponse;
import ru.omickron.myspb.service.dto.ProblemsPageResponse;
import ru.omickron.myspb.service.dto.ShortProblemResponse;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ProblemsService {
    @NonNull
    private final ReasonGroupService reasonGroupService;
    @NonNull
    private final HttpService httpService;
    @NonNull
    private final RequestContext requestContext;

    @NonNull
    public List<ShortProblemResponse> getProblems() {
        return httpService.get( Api.MY_PROBLEMS, ProblemsPageResponse.class ).getResults();
    }

    @NonNull
    public ProblemResponse getProblem( @NonNull Long id ) {
        return httpService.get( Api.PROBLEMS + id, ProblemResponse.class );
    }

    @NonNull
    public ProblemResponse createProblem( @NonNull Long reasonGroupId, @NonNull Double latitude,
            @NonNull Double longitude, @NonNull MultipartFile[] files ) {
        User user = requestContext.getUser().orElseThrow( UserNotFoundException :: new );
        ReasonGroup reasonGroup = reasonGroupService.findByUserAndId( user, reasonGroupId )
                .orElseThrow( ReasonGroupNotFoundException :: new );
        if (isNull( reasonGroup.getBody() )) {
            throw new NoBodyInReasonGroupException();
        }
        if (isNull( reasonGroup.getReasonId() )) {
            throw new NoReasonInReasonGroupException();
        }

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add( "body", reasonGroup.getBody() );
        body.add( "latitude", latitude );
        body.add( "longitude", longitude );
        body.add( "reason", reasonGroup.getReasonId() );
        for (MultipartFile file : files) {
            body.add( "files", file.getResource() );
        }
        CreatedProblemResponse response = httpService.post( Api.PROBLEMS, body, CreatedProblemResponse.class );
        return getProblem( response.getId() );
    }
}
