package ru.omickron.myspb.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.aspect.RefreshTokenIfRequired;
import ru.omickron.myspb.service.ReasonService;
import ru.omickron.myspb.service.dto.CityObjectResponse;

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
