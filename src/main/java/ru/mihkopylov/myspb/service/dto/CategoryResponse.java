package ru.mihkopylov.myspb.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private List<ReasonResponse> reasons = new ArrayList<>();
}
