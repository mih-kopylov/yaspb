package ru.mihkopylov.myspb.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ReasonResponse {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    @JsonProperty("wizard_widget")
    private PositionType positionType;
}
