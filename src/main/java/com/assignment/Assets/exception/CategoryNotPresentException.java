package com.assignment.Assets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is CategoryNotPresentException class.
 * @author Karan
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryNotPresentException extends RuntimeException {

    public CategoryNotPresentException(String exception) {
        super(exception);
    }
}
