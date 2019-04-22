package ru.omickron.myspb.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.controller.dto.CreateReasonGroupRequest;
import ru.omickron.myspb.controller.dto.ReasonGroupResponse;
import ru.omickron.myspb.controller.dto.TokenHeader;
import ru.omickron.myspb.exception.ReasonGroupNotFoundException;
import ru.omickron.myspb.exception.UserNotFoundException;
import ru.omickron.myspb.model.ReasonGroup;
import ru.omickron.myspb.model.User;
import ru.omickron.myspb.service.ReasonGroupService;
import ru.omickron.myspb.service.UserService;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(Const.REST + "/reasonGroups")
@AllArgsConstructor
@Slf4j
public class ReasonGroupController {
    @NonNull
    private final ReasonGroupService reasonGroupService;
    @NonNull
    private final UserService userService;

    @GetMapping
    public List<ReasonGroupResponse> getGroups( @RequestHeader("token") @Valid TokenHeader tokenHeader ) {
        log.debug( "get reason groups" );
        Optional<User> user = userService.findUserByToken( tokenHeader.getAccessToken() );
        if (user.isEmpty()) {
            throw new UserNotFoundException( "Can't find user by access token" );
        }
        return reasonGroupService.findByUser( user.get() )
                .stream()
                .map( ReasonGroupResponse :: new )
                .collect( toList() );
    }

    @PostMapping
    public ReasonGroupResponse createGroup( @RequestHeader("token") TokenHeader tokenHeader,
            @RequestBody @Valid CreateReasonGroupRequest request ) {
        log.debug( "create reason group" );
        User user =
                userService.findUserByToken( tokenHeader.getAccessToken() ).orElseThrow( UserNotFoundException :: new );
        ReasonGroup reasonGroup = reasonGroupService.createGroup(tokenHeader.toToken(), user, request.getName(), request.getParentId(), request.getReasonId() );
        return new ReasonGroupResponse( reasonGroup );
    }

    @PutMapping("/{id}")
    public ReasonGroupResponse updateGroup( @RequestHeader("token") TokenHeader tokenHeader,
            @PathVariable("id") Long id, @RequestBody @Valid CreateReasonGroupRequest request ) {
        log.debug( "update reason group" );
        User user =
                userService.findUserByToken( tokenHeader.getAccessToken() ).orElseThrow( UserNotFoundException :: new );
        ReasonGroup reasonGroup =
                reasonGroupService.findByUserAndId( user, id ).orElseThrow( ReasonGroupNotFoundException :: new );
        return new ReasonGroupResponse(
                reasonGroupService.updateReasonGroup(tokenHeader.toToken(), reasonGroup, request.getName(), request.getParentId(), request.getReasonId() ) );
    }

    @DeleteMapping("/{id}")
    public void deleteGroup( @RequestHeader("token") TokenHeader tokenHeader, @PathVariable("id") Long id ) {
        log.debug( "delete reason group" );
        User user =
                userService.findUserByToken( tokenHeader.getAccessToken() ).orElseThrow( UserNotFoundException :: new );
        ReasonGroup reasonGroup =
                reasonGroupService.findByUserAndId( user, id ).orElseThrow( ReasonGroupNotFoundException :: new );
        reasonGroupService.deleteReasonGroup( reasonGroup );
    }
}
