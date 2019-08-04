package ru.mihkopylov.myspb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserToImportNotFoundException extends AbstractFormattedException {
    public UserToImportNotFoundException() {
        super( "Can't find user by login" );
    }
}
