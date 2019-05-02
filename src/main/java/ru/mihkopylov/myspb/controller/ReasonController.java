package ru.mihkopylov.myspb.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mihkopylov.myspb.Const;
import ru.mihkopylov.myspb.service.ReasonService;
import ru.mihkopylov.myspb.service.dto.CityObjectResponse;
import ru.mihkopylov.myspb.aspect.RefreshTokenIfRequired;

@RestController
@RequestMapping(Const.REST + "/reasons")
@AllArgsConstructor
@Slf4j
public class ReasonController {
    @NonNull
    private final ReasonService reasonService;

    @GetMapping
    @RefreshTokenIfRequired
    public List<CityObjectResponse> getReasons() {
        log.debug( "get reasons" );
        return reasonService.getReasons();
    }
}
