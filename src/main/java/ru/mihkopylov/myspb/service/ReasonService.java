package ru.mihkopylov.myspb.service;

import com.google.common.base.Suppliers;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import ru.mihkopylov.myspb.Api;
import ru.mihkopylov.myspb.service.dto.CityObjectResponse;

@Service
@AllArgsConstructor
public class ReasonService {
    @NonNull
    private final HttpService httpService;
    private final Supplier<List<CityObjectResponse>> getReasonsSupplier =
            Suppliers.memoizeWithExpiration( this :: loadReasons, 1, TimeUnit.HOURS );

    @NonNull
    public List<CityObjectResponse> getReasons() {
        return getReasonsSupplier.get();
    }

    @NonNull
    private List<CityObjectResponse> loadReasons() {
        ParameterizedTypeReference<List<CityObjectResponse>> responseType = new ParameterizedTypeReference<>() {
        };
        return httpService.get( Api.CLASSIFIER, responseType, Map.of() );
    }
}
