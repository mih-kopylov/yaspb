package ru.omickron.myspb.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import ru.omickron.myspb.Api;
import ru.omickron.myspb.service.dto.CityObjectResponse;
import ru.omickron.myspb.service.dto.Token;

@Service
@AllArgsConstructor
public class ReasonService {
    @NonNull
    private final HttpService httpService;

    @NonNull
    public List<CityObjectResponse> getReasons( @NonNull Token token ) {
        ParameterizedTypeReference<List<CityObjectResponse>> responseType = new ParameterizedTypeReference<>() {
        };
        return httpService.get( token, Api.CLASSIFIER, responseType );
    }
}
