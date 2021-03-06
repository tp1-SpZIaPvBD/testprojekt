package sk.timak.testprojekt.controller.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sk.timak.testprojekt.controller.advice.exception.WrongFormatException;
import sk.timak.testprojekt.controller.advice.exception.WrongQueryException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ WrongFormatException.class })
    protected ResponseEntity<Object> handleWrongFormat(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.toString(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({ WrongQueryException.class })
    protected ResponseEntity<Object> handleWrongQuery(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.toString(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
