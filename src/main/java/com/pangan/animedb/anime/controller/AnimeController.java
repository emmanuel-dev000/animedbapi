package com.pangan.animedb.anime.controller;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.service.AnimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/anime")
public class AnimeController {

    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public ResponseEntity<String> getAllAnime() {
        return ResponseEntity.ok("Get all animes");
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getAnimeById(@PathVariable("id") String id) {
        return ResponseEntity.ok("Get anime: " + id);
    }

    @PostMapping
    public ResponseEntity<String> addAnime(@RequestBody AnimeRequestDto animeRequestDto) {
        return ResponseEntity.ok(
            animeService.addAnime(animeRequestDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimeById(@PathVariable("id") String id) {
        return ResponseEntity.ok("Anime ID: " + id + " was deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateAnimeById(@PathVariable("id") String id,
                                                  @RequestBody AnimeRequestDto animeRequestDto) {
        return ResponseEntity.ok("Anime ID: " + id + " was updated to: " + animeRequestDto.name());
    }
}
