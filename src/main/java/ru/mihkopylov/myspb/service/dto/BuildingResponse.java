package ru.mihkopylov.myspb.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingResponse {
    private Long id;
    private Double latitude;
    private Double longitude;
    @JsonProperty("full_address")
    private String address;
}
