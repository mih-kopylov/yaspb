package ru.omickron.myspb.controller.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import ru.omickron.myspb.service.dto.Token;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenHeader {
    @NotBlank
    private String accessToken;
    @NotBlank
    private String refreshToken;

    @NonNull
    public Token toToken() {
        return new Token( accessToken, refreshToken );
    }
}
