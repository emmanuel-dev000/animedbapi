package com.pangan.animedb.anime.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/anime")
public class AnimeController {

    @GetMapping
    public ResponseEntity<String> getAllAnime() {
        return ResponseEntity.ok("Get all animes");
    }
}
