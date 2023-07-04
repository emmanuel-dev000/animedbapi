package com.pangan.animedb.anime.exception;

import com.pangan.animedb.exception.ApiException;

public class AnimeNotFoundException extends ApiException {
    public static final String TITLE = "Anime not found.";
    public static final String ERROR_MESSAGE = "No anime found. Sorry. <( _ _ )>";

    public AnimeNotFoundException() {
        super(TITLE, ERROR_MESSAGE);
    }
}
