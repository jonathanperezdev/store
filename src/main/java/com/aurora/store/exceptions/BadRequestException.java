package com.aurora.store.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String cause){
        super(cause);
    }

}
