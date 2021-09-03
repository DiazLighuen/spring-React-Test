package com.galeno.exception;

public class InvalidFilterCriteriaException extends RuntimeException{
    public InvalidFilterCriteriaException(String message) {
        super(message);
    }

    public InvalidFilterCriteriaException(){}
}
