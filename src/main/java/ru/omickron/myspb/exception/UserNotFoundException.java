package ru.omickron.myspb.exception;

import lombok.NonNull;

public class UserNotFoundException extends AbstractFormattedException {
    public UserNotFoundException() {
        super( "Can't find user" );
    }

    public UserNotFoundException( @NonNull String message, @NonNull Object... params ) {
        super( message, params );
    }
}
