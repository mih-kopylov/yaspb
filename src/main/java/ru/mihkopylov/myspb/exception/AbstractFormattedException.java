package ru.mihkopylov.myspb.exception;

import lombok.NonNull;

public abstract class AbstractFormattedException extends RuntimeException {
    public AbstractFormattedException( @NonNull String message, @NonNull Object... params ) {
        super( String.format( message, params ) );
    }
}
