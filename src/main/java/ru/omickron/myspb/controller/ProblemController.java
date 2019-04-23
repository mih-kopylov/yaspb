package ru.omickron.myspb.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.controller.dto.CreateProblemRequest;
import ru.omickron.myspb.controller.dto.TokenHeader;
import ru.omickron.myspb.service.ProblemsService;
import ru.omickron.myspb.service.dto.ProblemResponse;
import ru.omickron.myspb.service.dto.ShortProblemResponse;

@RestController
@RequestMapping(Const.REST + "/problems")
@AllArgsConstructor
@Slf4j
public class ProblemController {
    @NonNull
    private final ProblemsService problemsService;

    @GetMapping
    public List<ShortProblemResponse> getProblems( @RequestHeader("token") TokenHeader tokenHeader ) {
        log.debug( "get problems" );
        return problemsService.getProblems( tokenHeader.toToken() );
    }

    @GetMapping("/{id}")
    public ProblemResponse getProblem( @RequestHeader("token") TokenHeader tokenHeader, @PathVariable("id") Long id ) {
        log.debug( "get problem: id={}", id );
        return problemsService.getProblem( tokenHeader.toToken(), id );
    }

    @PostMapping
    public ProblemResponse createProblem( @RequestHeader("token") TokenHeader tokenHeader,
            @ModelAttribute @Valid CreateProblemRequest request ) {
        log.debug( "create problem" );
        return problemsService.createProblem( tokenHeader.toToken(), request.getReasonGroupId(), request.getLatitude(),
                request.getLongitude(), request.getFiles() );
    }
}
