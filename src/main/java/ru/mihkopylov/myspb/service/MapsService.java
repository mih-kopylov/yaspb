package ru.mihkopylov.myspb.service;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.mihkopylov.myspb.Api;
import ru.mihkopylov.myspb.service.dto.BuildingResponse;
import ru.mihkopylov.myspb.service.dto.NearestBuildingsResponse;

@Service
@AllArgsConstructor
public class MapsService {
    @NonNull
    private final HttpService httpService;

    @NonNull
    public BuildingResponse getNearestBuilding( @NonNull Double latitude, @NonNull Double longitude ) {
        return httpService.get( Api.NEAREST, NearestBuildingsResponse.class,
                Map.of( "latitude", latitude, "longitude", longitude ) )
                .getBuildings()
                .stream()
                .findFirst()
                .orElseThrow();
    }
}
