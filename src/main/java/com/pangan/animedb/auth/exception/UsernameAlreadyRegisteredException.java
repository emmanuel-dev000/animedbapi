package com.pangan.animedb.auth.exception;

import com.pangan.animedb.exceptionhandler.ApiException;

public class UsernameAlreadyRegisteredException extends ApiException {

    public static final String TITLE = "Username already registered.";
    public static final String ERROR_MESSAGE = "Username is already registered. Please choose another. ヾ(•ω•`)o";

    public UsernameAlreadyRegisteredException() {
        super(TITLE, ERROR_MESSAGE);
    }
}
