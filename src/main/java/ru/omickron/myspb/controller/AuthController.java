package ru.omickron.myspb.controller;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.controller.dto.LoginRequest;
import ru.omickron.myspb.service.LoginService;

@RestController
@RequestMapping(Const.REST + "/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    @NonNull
    private final LoginService loginService;

    /**
     * This method returns ResponseEntity as a workaround of Srping's feature
     * Actually no response is required here.
     * The user's token will be put to headers in {@link ru.omickron.myspb.interceptor.AuthAfterHandleResponseBodyAdvice}
     * But if the method returns {@code void}, the token won't be written to response headers.
     * So returning ResponseEntity without a body is a workaround of this feature.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login( @RequestBody @Valid LoginRequest request ) {
        log.debug( "login user: login={}", request.getLogin() );
        loginService.login( request.getLogin(), request.getPassword() );
        return ResponseEntity.ok().build();
    }
}
