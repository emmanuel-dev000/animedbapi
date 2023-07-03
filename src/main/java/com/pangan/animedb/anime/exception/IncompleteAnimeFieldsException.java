package com.pangan.animedb.anime.exception;

import com.pangan.animedb.global.exception.BusinessException;

public class IncompleteAnimeFieldsException extends BusinessException {
    public static final String TITLE = "Incomplete fields.";
    public static final String ERROR_MESSAGE = "Input fields must not be null.";

    public IncompleteAnimeFieldsException() {
        super(TITLE, ERROR_MESSAGE);
    }
}
