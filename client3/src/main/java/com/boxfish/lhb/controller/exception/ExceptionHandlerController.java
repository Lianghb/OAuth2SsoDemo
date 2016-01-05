package com.boxfish.lhb.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by boxfish on 15/12/19.
 */

@ControllerAdvice
public class ExceptionHandlerController {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public Object handleAllException(Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
    }
}
