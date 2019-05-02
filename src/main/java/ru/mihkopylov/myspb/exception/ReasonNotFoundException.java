package ru.mihkopylov.myspb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReasonNotFoundException extends AbstractFormattedException {
    public ReasonNotFoundException() {
        super( "Can't find reason" );
    }
}
