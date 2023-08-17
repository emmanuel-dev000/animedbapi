package com.pangan.animedb.genre.exception;

import com.pangan.animedb.exceptionhandler.ApiException;

public class IncompleteGenreFieldsException extends ApiException {

    public static final String TITLE = "Incomplete genre fields.";
    public static final String ERROR_MESSAGE = "Input fields must not be null. （；´д｀）ゞ";

    public IncompleteGenreFieldsException() {
        super(TITLE, ERROR_MESSAGE);
    }
}