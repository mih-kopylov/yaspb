package ru.omickron.myspb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoReasonInReasonGroupException extends AbstractFormattedException {
    public NoReasonInReasonGroupException() {
        super( "Reason group should have reason" );
    }
}
