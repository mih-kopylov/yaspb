package ru.omickron.myspb.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omickron.myspb.Const;
import ru.omickron.myspb.controller.dto.TokenHeader;
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
    public List<CityObjectResponse> getReasons( @RequestHeader("token") @Valid TokenHeader tokenHeader ) {
        log.debug( "get reasons" );
        return reasonService.getReasons( tokenHeader.toToken() );
    }
}
