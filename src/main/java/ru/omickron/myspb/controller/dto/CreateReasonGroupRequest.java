package ru.omickron.myspb.controller.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReasonGroupRequest {
    @NotBlank
    private String name;
    private Long parentId;
}
