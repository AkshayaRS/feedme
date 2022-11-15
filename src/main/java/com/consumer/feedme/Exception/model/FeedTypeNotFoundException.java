package com.consumer.feedme.Exception.model;

public class FeedTypeNotFoundException extends RuntimeException {

    public String errorMessage;
    public FeedTypeNotFoundException(String message) {
        this.errorMessage=message;
    }
}
