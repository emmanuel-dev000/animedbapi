package com.pangan.animedb.anime.dao;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.dto.AnimeResponsePageDto;
import com.pangan.animedb.genre.dao.Genre;
import com.pangan.animedb.tag.dao.Tag;
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

    @GetMapping("/")
    public ResponseEntity<AnimeResponsePageDto> getAllAnime(
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageSize") Integer pageSize) {
        return ResponseEntity.ok(animeService.getAllAnimeInPage(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> getAnimeById(@PathVariable("id") String id) {
        return ResponseEntity.ok(animeService.getAnimeById(id));
    }

    @PostMapping
    public ResponseEntity<AnimeResponseDto> addAnime(@RequestBody AnimeRequestDto animeRequestDto) {
        return ResponseEntity.ok(animeService.addAnime(animeRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<List<AnimeResponseDto>> deleteAnimeListByAnimeIdList(@RequestBody List<String> animeIdList) {
        return ResponseEntity.ok(animeService.deleteAnimeListByAnimeIdList(animeIdList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<AnimeResponseDto>> deleteAnimeById(@PathVariable("id") String id) {
        return ResponseEntity.ok(animeService.deleteAnimeById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> updateAnimeById(@PathVariable("id") String id, @RequestBody AnimeRequestDto animeRequestDto) {
        return ResponseEntity.ok(animeService.updateAnimeById(id, animeRequestDto));
    }

    @PostMapping("/{id}/genres")
    public ResponseEntity<AnimeResponseDto> addGenreListToAnimeById(@PathVariable("id") String id, @RequestBody List<Genre> genreList) {
        return ResponseEntity.ok(animeService.addGenreListToAnimeById(id, genreList));
    }

    @DeleteMapping("{id}/genres")
    public ResponseEntity<AnimeResponseDto> deleteGenreInAnimeById(@PathVariable("id") String id, @RequestBody Genre genre) {
        return ResponseEntity.ok(animeService.deleteGenreInAnimeById(id, genre));
    }

    @PostMapping("/{id}/tags")
    public ResponseEntity<AnimeResponseDto> addTagToAnimeById(@PathVariable("id") String id, @RequestBody List<Tag> tagList) {
        return ResponseEntity.ok(animeService.addTagListInAnimeById(id, tagList));
    }

    @DeleteMapping("{id}/tags")
    public ResponseEntity<AnimeResponseDto> deleteTagInAnimeById(@PathVariable("id") String id, @RequestBody Tag tag) {
        return ResponseEntity.ok(animeService.deleteTagInAnimeById(id, tag));
    }
}
