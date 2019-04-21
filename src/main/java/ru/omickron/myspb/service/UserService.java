package ru.omickron.myspb.service;

import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.omickron.myspb.Api;
import ru.omickron.myspb.service.dto.ProfileResponse;
import ru.omickron.myspb.service.dto.Token;

@Service
@AllArgsConstructor
public class UserService {
    @NonNull
    public ProfileResponse getProfile( @NonNull Token token ) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth( token.getAccessToken() );
        RequestEntity<String> requestEntity = new RequestEntity<>( headers, HttpMethod.GET, URI.create( Api.PROFILE ) );
        return restTemplate.exchange( requestEntity, ProfileResponse.class ).getBody();
    }
}
