package com.pangan.animedb.exception;

public class NoAnimeContentException extends BusinessException {
    public static final String TITLE = "No contents.";
    public static final String ERROR_MESSAGE = "Input fields must not be null.";

    public NoAnimeContentException() {
        super(TITLE, ERROR_MESSAGE);
    }
}
