package ru.mihkopylov.myspb.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import ru.mihkopylov.myspb.service.dto.CityObjectResponse;
import ru.mihkopylov.myspb.Api;

@Service
@AllArgsConstructor
public class ReasonService {
    @NonNull
    private final HttpService httpService;

    @NonNull
    public List<CityObjectResponse> getReasons() {
        ParameterizedTypeReference<List<CityObjectResponse>> responseType = new ParameterizedTypeReference<>() {
        };
        return httpService.get( Api.CLASSIFIER, responseType );
    }
}
