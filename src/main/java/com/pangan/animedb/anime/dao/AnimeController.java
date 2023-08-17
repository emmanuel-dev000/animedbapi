package com.pangan.animedb.anime.dao;

import com.pangan.animedb.anime.dto.AnimeImageDetailDto;
import com.pangan.animedb.anime.dto.AnimePageDto;
import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.genre.dao.Genre;
import com.pangan.animedb.tag.dao.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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
    public ResponseEntity<AnimePageDto> getAllAnime(
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageSize") Integer pageSize) {
        return ResponseEntity.ok(animeService.getAllAnimeInPage(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> getAnimeById(@PathVariable("id") String id) {
        return ResponseEntity.ok(animeService.getAnimeById(id));
    }

    @GetMapping("/{id}/image-detail")
    public ResponseEntity<AnimeImageDetailDto> getAnimeImageDetailById(@PathVariable("id") String id) {
        return ResponseEntity.ok(animeService.getAnimeImageDetailById(id));
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
    public ResponseEntity<String> deleteAnimeById(@PathVariable("id") String id) {
        return ResponseEntity.ok(animeService.deleteAnimeById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> updateAnimeById(@PathVariable("id") String id, @RequestBody AnimeRequestDto animeRequestDto) {
        return ResponseEntity.ok(animeService.updateAnimeById(id, animeRequestDto));
    }

    @GetMapping("/{id}/genres")
    public ResponseEntity<List<Genre>> getGenreListInAnimeById(@PathVariable("id") String id) {
        return ResponseEntity.ok(animeService.getGenreListInAnimeById(id));
    }

    @PatchMapping("/{id}/genres")
    public ResponseEntity<AnimeResponseDto> updateGenreListInAnimeById(@PathVariable("id") String id, @RequestBody List<Genre> genreList) {
        return ResponseEntity.ok(animeService.updateGenreListInAnimeById(id, genreList));
    }

    @GetMapping("/{id}/tags")
    public ResponseEntity<List<Tag>> getTagListInAnimeById(@PathVariable("id") String id) {
        return ResponseEntity.ok(animeService.getTagListInAnimeById(id));
    }

    @PatchMapping("/{id}/tags")
    public ResponseEntity<AnimeResponseDto> updateTagListInAnimeById(@PathVariable("id") String id, @RequestBody List<Tag> tagList) {
        return ResponseEntity.ok(animeService.updateTagListInAnimeById(id, tagList));
    }
}
