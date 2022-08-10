package com.udemy.spring.rest.exception_handling;

public class NoSuckEmployeeException extends RuntimeException{

    public NoSuckEmployeeException(String message) {
        super(message);
    }
}
