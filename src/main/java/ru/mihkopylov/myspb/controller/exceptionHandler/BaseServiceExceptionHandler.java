package ru.mihkopylov.myspb.controller.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mihkopylov.myspb.controller.dto.ErrorResponse;
import ru.mihkopylov.myspb.exception.BaseServiceException;

@ControllerAdvice
@Slf4j
public class BaseServiceExceptionHandler {
    @ExceptionHandler(BaseServiceException.class)
    public ResponseEntity<ErrorResponse> handle( BaseServiceException exception ) {
        log.error( "Exception", exception );
        return ResponseEntity.status( exception.getStatus() ).body( new ErrorResponse( exception.getMessage() ) );
    }
}
