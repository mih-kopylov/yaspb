package ru.omickron.myspb.service;

import java.net.URI;
import java.util.List;
import lombok.NonNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.omickron.myspb.Api;
import ru.omickron.myspb.service.dto.CityObjectResponse;
import ru.omickron.myspb.service.dto.Token;

@Service
public class ReasonService {
    @NonNull
    public List<CityObjectResponse> getReasons( @NonNull Token token ) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth( token.getAccessToken() );
        RequestEntity<String> requestEntity =
                new RequestEntity<>( headers, HttpMethod.GET, URI.create( Api.CLASSIFIER ) );
        return restTemplate.exchange( requestEntity, new ParameterizedTypeReference<List<CityObjectResponse>>() {
        } ).getBody();
    }
}
