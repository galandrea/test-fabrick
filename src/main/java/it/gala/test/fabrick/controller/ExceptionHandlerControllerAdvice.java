package it.gala.test.fabrick.controller;

import it.gala.test.fabrick.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);

    @ExceptionHandler(value = {
            ApplicationException.class
    })
    protected ResponseEntity<Object> handleAppException(ApplicationException ex, WebRequest request) {

        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("status", ex.getHttpStatus().value());
        errorAttributes.put("error", ex.getHttpStatus().getReasonPhrase());
        errorAttributes.put("message", ex.getMessage());
        errorAttributes.put("path", ((ServletWebRequest)request).getRequest().getRequestURI());

        return new ResponseEntity<>(errorAttributes, ex.getHttpStatus());
    }

    @ExceptionHandler(value = {
            Exception.class
    })
    protected ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {

        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorAttributes.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorAttributes.put("message", ex.getMessage());
        errorAttributes.put("path", ((ServletWebRequest)request).getRequest().getRequestURI());

        log.error("", ex);
        return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
