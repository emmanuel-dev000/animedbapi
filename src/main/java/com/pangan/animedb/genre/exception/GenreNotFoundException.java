package com.pangan.animedb.genre.exception;

import com.pangan.animedb.exception.ApiException;

public class GenreNotFoundException extends ApiException {

    public static final String TITLE = "Genre not found.";
    public static final String ERROR_MESSAGE = "Genre was not found. Sorry ( ＞︿＜ )";

    public GenreNotFoundException() {
        super(TITLE, ERROR_MESSAGE);
    }
}