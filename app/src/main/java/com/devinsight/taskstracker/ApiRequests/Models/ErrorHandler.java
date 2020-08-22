package com.devinsight.taskstracker.ApiRequests.Models;

public class ErrorHandler {

    private String message;

    public ErrorHandler(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
