package ru.omickron.myspb.service;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.omickron.myspb.Api;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.service.dto.TokenResponse;

@Service
@AllArgsConstructor
public class LoginService {
    @NonNull
    private final UserService userService;

    @NonNull
    public TokenResponse login( @NonNull String login, @NonNull String password ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_FORM_URLENCODED );
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add( "username", login );
        map.add( "password", password );
        map.add( "grant_type", "password" );
        map.add( "client_id", Const.CLIENT_ID );
        map.add( "client_secret", Const.CLIENT_SECRET );
        HttpEntity<MultiValueMap<String, String>> authRequest = new HttpEntity<>( map, headers );

        TokenResponse response = Objects.requireNonNull(
                new RestTemplate().postForEntity( Api.TOKEN, authRequest, TokenResponse.class ).getBody() );
        userService.updateUser( login, response.getAccessToken() );
        return response;
    }
}
