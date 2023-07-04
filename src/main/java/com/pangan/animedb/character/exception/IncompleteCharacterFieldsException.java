package com.pangan.animedb.character.exception;

import com.pangan.animedb.exceptionhandler.ApiException;

public class IncompleteCharacterFieldsException extends ApiException {

    public static final String TITLE = "Incomplete fields.";
    public static final String ERROR_MESSAGE = "Input fields must not be null. ~(>_<。)＼";

    public IncompleteCharacterFieldsException() {
        super(TITLE, ERROR_MESSAGE);
    }
}
