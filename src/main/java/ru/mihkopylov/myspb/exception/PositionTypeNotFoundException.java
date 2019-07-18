package ru.mihkopylov.myspb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PositionTypeNotFoundException extends AbstractFormattedException {
    public PositionTypeNotFoundException() {
        super( "Can't find position type" );
    }
}
