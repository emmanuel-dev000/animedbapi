package com.pangan.animedb.global.exception;

import java.time.ZonedDateTime;

public record ResponseException(
        String title,
        String errorMessage,
        ZonedDateTime timeStamp) {
}
