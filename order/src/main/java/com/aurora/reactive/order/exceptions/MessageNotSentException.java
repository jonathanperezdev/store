package com.aurora.reactive.order.exceptions;

public class MessageNotSentException extends RuntimeException{
    public MessageNotSentException(String message) {
        super(message);
    }
}
