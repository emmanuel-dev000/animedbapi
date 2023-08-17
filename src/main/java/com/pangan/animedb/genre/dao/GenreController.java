package com.pangan.animedb.genre.dao;

import com.pangan.animedb.genre.dto.GenreRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") String id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @PostMapping
    public ResponseEntity<Genre> addAnime(@RequestBody GenreRequestDto genreRequestDto) {
        return ResponseEntity.ok(genreService.addGenre(genreRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimeById(@PathVariable("id") String id) {
        return ResponseEntity.ok(genreService.deleteGenreById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Genre> updateAnimeById(@PathVariable("id") String id, @RequestBody GenreRequestDto genreRequestDto) {
        return ResponseEntity.ok(genreService.updateGenreById(id, genreRequestDto));
    }
}
