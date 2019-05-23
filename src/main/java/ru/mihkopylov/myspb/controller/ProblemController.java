package ru.mihkopylov.myspb.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mihkopylov.myspb.Const;
import ru.mihkopylov.myspb.aspect.RefreshTokenIfRequired;
import ru.mihkopylov.myspb.controller.dto.CreateProblemRequest;
import ru.mihkopylov.myspb.service.ProblemsService;
import ru.mihkopylov.myspb.service.dto.ProblemResponse;
import ru.mihkopylov.myspb.service.dto.ShortProblemResponse;

@RestController
@RequestMapping(Const.REST + "/problems")
@AllArgsConstructor
@Slf4j
public class ProblemController {
    @NonNull
    private final ProblemsService problemsService;

    @GetMapping
    @RefreshTokenIfRequired
    public List<ShortProblemResponse> getProblems() {
        log.debug( "get problems" );
        return problemsService.getProblems();
    }

    @GetMapping("/{id}")
    @RefreshTokenIfRequired
    public ProblemResponse getProblem( @PathVariable("id") Long id ) {
        log.debug( "get problem: id={}", id );
        return problemsService.getProblem( id );
    }

    @PostMapping
    @RefreshTokenIfRequired
    public ProblemResponse createProblem( @ModelAttribute @Valid CreateProblemRequest request ) {
        log.debug( "create problem" );
        log.info( "start problem creating" );
        try {
            return problemsService.createProblem( request.getReasonGroupId(), request.getLatitude(),
                    request.getLongitude(), request.getFiles() );
        } finally {
            log.info( "end problem creating" );
        }
    }
}
