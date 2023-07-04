package com.pangan.animedb.exception;

public class ApiException extends RuntimeException {
    private final String title;

    public ApiException(String title, String message) {
        super(message);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
