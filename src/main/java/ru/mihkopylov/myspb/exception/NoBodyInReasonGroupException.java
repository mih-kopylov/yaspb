package ru.mihkopylov.myspb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoBodyInReasonGroupException extends AbstractFormattedException {
    public NoBodyInReasonGroupException() {
        super( "Reason group should have body" );
    }
}
