package ru.omickron.myspb.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.omickron.myspb.Api;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.exception.UserNotFoundException;
import ru.omickron.myspb.interceptor.RequestContext;
import ru.omickron.myspb.model.User;
import ru.omickron.myspb.service.dto.Token;
import ru.omickron.myspb.service.dto.TokenResponse;

@Service
@AllArgsConstructor
public class LoginService {
    @NonNull
    private final HttpService httpService;
    @NonNull
    private final UserService userService;
    @NonNull
    private final RequestContext requestContext;

    @NonNull
    public TokenResponse login( @NonNull String login, @NonNull String password ) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add( "username", login );
        body.add( "password", password );
        body.add( "grant_type", "password" );
        body.add( "client_id", Const.CLIENT_ID );
        body.add( "client_secret", Const.CLIENT_SECRET );

        TokenResponse response = httpService.post( Api.TOKEN, body, TokenResponse.class );
        userService.updateUser( login, response.getAccessToken() );
        requestContext.setToken( Token.fromResponse( response ) );
        return response;
    }

    @NonNull
    public TokenResponse refreshToken() {
        User user = requestContext.getUser().orElseThrow( UserNotFoundException :: new );
        Optional<Token> token = requestContext.getToken();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        token.ifPresent( value -> body.add( "refresh_token", value.getRefreshToken() ) );
        body.add( "grant_type", "refresh_token" );
        body.add( "client_id", Const.CLIENT_ID );
        body.add( "client_secret", Const.CLIENT_SECRET );

        TokenResponse response = httpService.post( Api.TOKEN, body, TokenResponse.class );
        userService.updateUser( user.getLogin(), response.getAccessToken() );
        return response;
    }
}
