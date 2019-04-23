package ru.omickron.myspb.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemResponse {
    private Long id;
    private Long reason;
    @JsonProperty("reason_name")
    private String reasonName;
    private Double latitude;
    private Double longitude;
}
