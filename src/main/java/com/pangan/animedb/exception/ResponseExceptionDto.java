package com.pangan.animedb.exception;

import java.time.ZonedDateTime;

public record ResponseExceptionDto(
        String title,
        String errorMessage,
        ZonedDateTime timeStamp) {
}
