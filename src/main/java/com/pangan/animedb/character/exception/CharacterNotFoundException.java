package com.pangan.animedb.character.exception;

import com.pangan.animedb.exceptionhandler.ApiException;

public class CharacterNotFoundException extends ApiException {

    public static final String TITLE = "Character not found.";
    public static final String ERROR_MESSAGE = "No character found. Sorry. ( ╯︿╰  ;)";

    public CharacterNotFoundException() {
        super(TITLE, ERROR_MESSAGE);
    }
}
