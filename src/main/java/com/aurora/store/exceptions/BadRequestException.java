package com.aurora.store.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class BadRequestException extends RuntimeException{
    private final List<String> errors;
}
