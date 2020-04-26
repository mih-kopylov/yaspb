package ru.mihkopylov.myspb.exception;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
public class BaseServiceException extends AbstractFormattedException {
    @NonNull
    private final HttpStatus status;

    public BaseServiceException( @NonNull HttpStatus status, @NonNull String message, @NonNull Object... params ) {
        super( message, params );
        this.status = status;
    }
}
