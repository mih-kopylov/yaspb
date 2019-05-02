package ru.mihkopylov.myspb.service.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CityObjectResponse {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private List<CategoryResponse> categories = new ArrayList<>();
}
