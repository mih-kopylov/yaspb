package ru.omickron.myspb.controller;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.controller.dto.TokenRequest;
import ru.omickron.myspb.service.UserService;
import ru.omickron.myspb.service.dto.ProfileResponse;

@RestController
@RequestMapping(Const.REST + "/profile")
@AllArgsConstructor
@Slf4j
public class ProfileController {
    @NonNull
    private final UserService userService;

    @GetMapping
    public ProfileResponse getProfile( @RequestBody @Valid TokenRequest token ) {
        log.debug( "get own profile" );
        ProfileResponse profileResponse = userService.getProfile( token.toToken() );
        userService.updateUser( profileResponse.getEmail(), profileResponse.getFirstName(),
                profileResponse.getLastName() );
        return profileResponse;
    }
}
