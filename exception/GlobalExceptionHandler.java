package com.omniops.omniops_backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception ex) {

        ex.printStackTrace();

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));

        return ResponseEntity
                .status(500)
                .body(sw.toString());
    }
}