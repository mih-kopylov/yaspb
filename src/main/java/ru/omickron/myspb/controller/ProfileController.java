package ru.omickron.myspb.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.aspect.RefreshTokenIfRequired;
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
    @RefreshTokenIfRequired
    public ProfileResponse getProfile() {
        log.debug( "get own profile" );
        return userService.getProfile();
    }
}
