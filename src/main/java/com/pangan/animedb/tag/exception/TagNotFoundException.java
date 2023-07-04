package com.pangan.animedb.tag.exception;

import com.pangan.animedb.exceptionhandler.ApiException;

public class TagNotFoundException extends ApiException {
    public static final String TITLE = "Tag not found.";
    public static final String ERROR_MESSAGE = "No tag found. Sorry. (っ °Д °;)っ";

    public TagNotFoundException() {
        super(TITLE, ERROR_MESSAGE);
    }
}
