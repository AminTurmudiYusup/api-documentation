package com.nostra.movies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EmptyContentException extends RuntimeException{
    public EmptyContentException(String s){
        super(s);
    }
}
