package ru.mihkopylov.myspb.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.mihkopylov.myspb.Api;
import ru.mihkopylov.myspb.Const;
import ru.mihkopylov.myspb.model.User;
import ru.mihkopylov.myspb.service.dto.Token;
import ru.mihkopylov.myspb.service.dto.TokenResponse;
import ru.mihkopylov.myspb.exception.UserNotFoundException;
import ru.mihkopylov.myspb.interceptor.RequestContext;

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
