package com.test.tags.controller;

import com.test.tags.exception.TagNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author sanjoy
 * on 5/28/21
 */

@ControllerAdvice
public class TagNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TagNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(TagNotFoundException ex) {
        return ex.getMessage();
    }
}
