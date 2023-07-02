package com.pangan.animedb.exception;

import java.time.ZonedDateTime;

public class NoAnimeFoundException extends BusinessException {
    public static final String TITLE = "No anime found.";
    public static final String ERROR_MESSAGE = "No anime found. Sorry. <( _ _ )>";

    public NoAnimeFoundException() {
        super(TITLE, ERROR_MESSAGE);
    }
}
