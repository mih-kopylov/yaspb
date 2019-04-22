package ru.omickron.myspb.exception;

import lombok.NonNull;

public class ReasonGroupNotFoundException extends AbstractFormattedException {
    public ReasonGroupNotFoundException() {
        super( "Can't find reason group" );
    }

    public ReasonGroupNotFoundException( @NonNull String message, @NonNull Object... params ) {
        super( message, params );
    }
}
