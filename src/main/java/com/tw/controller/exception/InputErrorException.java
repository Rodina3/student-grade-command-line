package com.tw.controller.exception;

public class InputErrorException extends RuntimeException{
    public InputErrorException(String message) {
        super(message);
    }
}
