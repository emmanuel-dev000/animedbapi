package com.pangan.animedb.auth.exception;

import com.pangan.animedb.exceptionhandler.ApiException;

public class UsernameNotFoundException extends ApiException {

    public static final String TITLE = "Username name not found.";
    public static final String ERROR_MESSAGE = "Username was not found in the database. /(ㄒoㄒ)/~~";

    public UsernameNotFoundException() {
        super(TITLE, ERROR_MESSAGE);
    }
}
