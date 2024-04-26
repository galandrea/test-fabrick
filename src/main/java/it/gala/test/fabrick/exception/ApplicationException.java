package it.gala.test.fabrick.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ApplicationException(HttpStatus httpStatus, String body) {
        super(body);
        this.httpStatus = httpStatus;
    }
}
