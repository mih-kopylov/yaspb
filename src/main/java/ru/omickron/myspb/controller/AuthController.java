package ru.omickron.myspb.controller;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.controller.dto.LoginRequest;
import ru.omickron.myspb.controller.dto.TokenHeader;
import ru.omickron.myspb.service.LoginService;
import ru.omickron.myspb.service.dto.Token;

@RestController
@RequestMapping(Const.REST + "/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    @NonNull
    private final LoginService loginService;

    @PostMapping("/login")
    public Token login( @RequestBody @Valid LoginRequest request ) {
        log.debug( "login user: login={}", request.getLogin() );
        return Token.fromResponse( loginService.login( request.getLogin(), request.getPassword() ) );
    }

    @PostMapping("/refreshToken")
    public Token refreshToken( @RequestHeader("token") TokenHeader tokenHeader ) {
        log.debug( "refresh token" );
        return Token.fromResponse( loginService.refreshToken( tokenHeader.toToken() ) );
    }
}
