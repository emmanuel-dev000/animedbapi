package com.pangan.animedb.exception;

public class BusinessException extends RuntimeException {
    private final String title;

    public BusinessException(String title, String message) {
        super(message);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
