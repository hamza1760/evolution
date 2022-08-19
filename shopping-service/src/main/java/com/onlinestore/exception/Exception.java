package com.onlinestore.exception;

public class Exception extends RuntimeException {

    String message;
    int id;

    public Exception(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public Exception() {
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }
}
