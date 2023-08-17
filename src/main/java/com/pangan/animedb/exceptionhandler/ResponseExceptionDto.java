package com.pangan.animedb.exceptionhandler;

import java.time.ZonedDateTime;

public record ResponseExceptionDto(
        String title,
        String errorMessage,
        ZonedDateTime timeStamp) {
}
