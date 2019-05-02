package ru.omickron.myspb.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.aspect.RefreshTokenIfRequired;
import ru.omickron.myspb.controller.dto.CreateReasonGroupRequest;
import ru.omickron.myspb.controller.dto.ReasonGroupResponse;
import ru.omickron.myspb.exception.ReasonGroupNotFoundException;
import ru.omickron.myspb.exception.UserNotFoundException;
import ru.omickron.myspb.interceptor.RequestContext;
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
    @NonNull
    private final RequestContext requestContext;

    @GetMapping
    @RefreshTokenIfRequired
    public List<ReasonGroupResponse> getGroups() {
        log.debug( "get reason groups" );
        User user = requestContext.getUser().orElseThrow( UserNotFoundException :: new );
        return reasonGroupService.findByUser( user ).stream().map( ReasonGroupResponse :: new ).collect( toList() );
    }

    @PostMapping
    @RefreshTokenIfRequired
    public ReasonGroupResponse createGroup( @RequestBody @Valid CreateReasonGroupRequest request ) {
        log.debug( "create reason group" );
        User user = requestContext.getUser().orElseThrow( UserNotFoundException :: new );
        ReasonGroup reasonGroup =
                reasonGroupService.createGroup( user, request.getName(), request.getParentId(), request.getReasonId(),
                        request.getBody() );
        return new ReasonGroupResponse( reasonGroup );
    }

    @PutMapping("/{id}")
    @RefreshTokenIfRequired
    public ReasonGroupResponse updateGroup( @PathVariable("id") Long id,
            @RequestBody @Valid CreateReasonGroupRequest request ) {
        log.debug( "update reason group" );
        User user = requestContext.getUser().orElseThrow( UserNotFoundException :: new );
        ReasonGroup reasonGroup =
                reasonGroupService.findByUserAndId( user, id ).orElseThrow( ReasonGroupNotFoundException :: new );
        return new ReasonGroupResponse(
                reasonGroupService.updateReasonGroup( reasonGroup, request.getName(), request.getParentId(),
                        request.getReasonId(), request.getBody() ) );
    }

    @DeleteMapping("/{id}")
    @RefreshTokenIfRequired
    public void deleteGroup( @PathVariable("id") Long id ) {
        log.debug( "delete reason group" );
        User user = requestContext.getUser().orElseThrow( UserNotFoundException :: new );
        ReasonGroup reasonGroup =
                reasonGroupService.findByUserAndId( user, id ).orElseThrow( ReasonGroupNotFoundException :: new );
        reasonGroupService.deleteReasonGroup( reasonGroup );
    }
}
