package ru.mihkopylov.myspb.service.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class NearestBuildingsResponse {
    @NonNull
    private List<BuildingResponse> buildings = new ArrayList<>();
}
