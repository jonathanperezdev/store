package com.aurora.store.exceptions;

public class MessageNotSentException extends RuntimeException{
    public MessageNotSentException(String message) {
        super(message);
    }
}
