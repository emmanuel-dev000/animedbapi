package com.pangan.animedb.anime.exception;

import com.pangan.animedb.exception.ApiException;

public class IncompleteAnimeFieldsException extends ApiException {
    public static final String TITLE = "Incomplete fields.";
    public static final String ERROR_MESSAGE = "Input fields must not be null. (ノへ￣、)";

    public IncompleteAnimeFieldsException() {
        super(TITLE, ERROR_MESSAGE);
    }
}
