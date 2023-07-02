package com.pangan.animedb.anime.exception;

import com.pangan.animedb.global.exception.BusinessException;

public class NoAnimeContentException extends BusinessException {
    public static final String TITLE = "No contents.";
    public static final String ERROR_MESSAGE = "Input fields must not be null.";

    public NoAnimeContentException() {
        super(TITLE, ERROR_MESSAGE);
    }
}
