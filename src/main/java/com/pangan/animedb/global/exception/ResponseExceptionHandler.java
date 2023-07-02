package com.pangan.animedb.global.exception;

import com.pangan.animedb.anime.exception.NoAnimeContentException;
import com.pangan.animedb.anime.exception.NoAnimeFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ResponseException> handleNoAnimeFoundException(NoAnimeFoundException e) {
        ResponseException exception = new ResponseException(
                e.getTitle(),
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private ResponseEntity<ResponseException> handleNoAnimeContentException(NoAnimeContentException e) {
        ResponseException exception = new ResponseException(
                e.getTitle(),
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
