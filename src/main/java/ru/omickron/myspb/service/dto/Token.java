package ru.omickron.myspb.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @NonNull
    private String accessToken;
    @NonNull
    private String refreshToken;

    @NonNull
    public static Token fromResponse( @NonNull TokenResponse tokenResponse ) {
        return new Token( tokenResponse.getAccessToken(), tokenResponse.getRefreshToken() );
    }

    @Override
    public String toString() {
        return accessToken + ":" + refreshToken;
    }
}
