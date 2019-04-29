package ru.omickron.myspb.service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ru.omickron.myspb.service.dto.Token;

@Service
@Slf4j
public class HttpService {
    @NonNull
    public <T> T get( @NonNull Token token, @NonNull String url, @NonNull Class<T> responseClass ) {
        return get( token, url, ParameterizedTypeReference.forType( responseClass ) );
    }

    @NonNull
    public <T> T get( @NonNull Token token, @NonNull String url, @NonNull ParameterizedTypeReference<T> responseType ) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createHttpHeaders( token );
        RequestEntity<String> requestEntity = new RequestEntity<>( headers, HttpMethod.GET, URI.create( url ) );
        return wrapLoggingClientException( () -> restTemplate.exchange( requestEntity, responseType ).getBody() );
    }

    @NonNull
    public <T> T post( @NonNull Token token, @NonNull String url,
            @NonNull MultiValueMap<String, Object> multipartFormBody, @NonNull Class<T> responseClass ) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createHttpHeaders( token );
        headers.setContentType( MediaType.MULTIPART_FORM_DATA );
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>( multipartFormBody, headers );
        return wrapLoggingClientException(
                () -> restTemplate.postForEntity( URI.create( url ), httpEntity, responseClass ).getBody() );
    }

    @NonNull
    private <T> T wrapLoggingClientException( @NonNull Supplier<T> supplier ) {
        try {
            return supplier.get();
        } catch (HttpStatusCodeException e) {
            log.error( "Client error happened: {}",
                    new String( e.getResponseBodyAsByteArray(), StandardCharsets.UTF_8 ) );
            throw e;
        }
    }

    @NonNull
    private HttpHeaders createHttpHeaders( @NonNull Token token ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth( token.getAccessToken() );
        return headers;
    }
}
