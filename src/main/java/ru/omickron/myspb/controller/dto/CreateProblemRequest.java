package ru.omickron.myspb.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateProblemRequest {
    @NotNull
    private Long reasonGroupId;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotEmpty
    private MultipartFile[] files;
}
