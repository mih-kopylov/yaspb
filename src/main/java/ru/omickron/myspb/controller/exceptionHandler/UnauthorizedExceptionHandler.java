package ru.omickron.myspb.controller.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class UnauthorizedExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handle() {
    }
}
