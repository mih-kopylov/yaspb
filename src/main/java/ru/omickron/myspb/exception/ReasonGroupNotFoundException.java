package ru.omickron.myspb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReasonGroupNotFoundException extends AbstractFormattedException {
    public ReasonGroupNotFoundException() {
        super( "Can't find reason group" );
    }
}
