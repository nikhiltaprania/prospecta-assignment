package com.problem1.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private final LocalDateTime timestamp;
    private final String message;
    private final String uri;

    public ErrorDetails(LocalDateTime timestamp, String message, String uri) {
        this.timestamp = timestamp;
        this.message = message;
        this.uri = uri;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getUri() {
        return uri;
    }
}