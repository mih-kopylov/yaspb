package ru.mihkopylov.myspb.controller.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
