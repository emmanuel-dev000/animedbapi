package com.pangan.animedb.exception;

import com.pangan.animedb.anime.exception.IncompleteAnimeFieldsException;
import com.pangan.animedb.anime.exception.AnimeNotFoundException;
import com.pangan.animedb.genre.exception.GenreNotFoundException;
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
    private ResponseEntity<ResponseExceptionDto> handleAnimeNotFoundException(AnimeNotFoundException e) {
        ResponseExceptionDto exception = new ResponseExceptionDto(
                e.getTitle(),
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private ResponseEntity<ResponseExceptionDto> handleIncompleteAnimeFieldsException(IncompleteAnimeFieldsException e) {
        ResponseExceptionDto exception = new ResponseExceptionDto(
                e.getTitle(),
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ResponseExceptionDto> handleGenreNotFoundException(GenreNotFoundException e) {
        ResponseExceptionDto exception = new ResponseExceptionDto(
                e.getTitle(),
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
