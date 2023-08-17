package com.pangan.animedb.tag.exception;

import com.pangan.animedb.exceptionhandler.ApiException;

public class IncompleteTagFieldsException extends ApiException {

    public static final String TITLE = "Incomplete tag fields.";
    public static final String ERROR_MESSAGE = "Input fields must not be null. （＞人＜；）";

    public IncompleteTagFieldsException() {
        super(TITLE, ERROR_MESSAGE);
    }
}