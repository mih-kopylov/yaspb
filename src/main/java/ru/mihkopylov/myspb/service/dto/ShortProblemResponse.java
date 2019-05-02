package ru.mihkopylov.myspb.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortProblemResponse {
    private Long id;
    private String reason;
    /**
     * Use LocalDateTime since OurSpb doesn't provide us with time zone
     */
    private LocalDateTime dt;
    @JsonProperty("full_address")
    private String fullAddress;
    private Double latitude;
    private Double longitude;
}
