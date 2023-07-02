package com.pangan.animedb.anime.controller;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/anime")
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public ResponseEntity<List<AnimeResponseDto>> getAllAnime() {
        return ResponseEntity.ok(animeService.getAllAnime());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> getAnimeById(@PathVariable("id") String id) {
        return ResponseEntity.ok(animeService.getAnimeById(id));
    }

    @PostMapping
    public ResponseEntity<AnimeResponseDto> addAnime(@RequestBody AnimeRequestDto animeRequestDto) {
        return ResponseEntity.ok(animeService.addAnime(animeRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<AnimeResponseDto>> deleteAnimeById(@PathVariable("id") String id) {
        return ResponseEntity.ok(animeService.deleteAnimeById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> updateAnimeById(@PathVariable("id") String id, @RequestBody AnimeRequestDto animeRequestDto) {
        return ResponseEntity.ok(animeService.updateAnimeById(id, animeRequestDto));
    }
}
