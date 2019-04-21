package ru.omickron.myspb.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.controller.dto.ReasonGroupResponse;
import ru.omickron.myspb.controller.dto.TokenRequest;
import ru.omickron.myspb.exception.UserNotFoundException;
import ru.omickron.myspb.model.User;
import ru.omickron.myspb.service.ReasonGroupService;
import ru.omickron.myspb.service.UserService;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(Const.REST + "/reasonGroup")
@AllArgsConstructor
@Slf4j
public class ReasonGroupController {
    @NonNull
    private final ReasonGroupService reasonGroupService;
    @NonNull
    private final UserService userService;

    @GetMapping
    public List<ReasonGroupResponse> getGroups( @RequestBody @Valid TokenRequest tokenRequest ) {
        Optional<User> user = userService.findUserByToken( tokenRequest.getAccessToken() );
        if (user.isEmpty()) {
            throw new UserNotFoundException( "Can't find user by access token" );
        }
        return reasonGroupService.findByUser( user.get() )
                .stream()
                .map( ReasonGroupResponse :: new )
                .collect( toList() );
    }
}
