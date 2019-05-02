package ru.omickron.myspb.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import ru.omickron.myspb.Api;
import ru.omickron.myspb.service.dto.CityObjectResponse;

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
