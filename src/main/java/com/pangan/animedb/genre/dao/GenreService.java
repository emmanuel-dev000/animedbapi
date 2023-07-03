package com.pangan.animedb.genre.dao;

import com.pangan.animedb.genre.dto.GenreRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(String id) {
        return genreRepository.findById(id).get();
    }

    public Genre addGenre(GenreRequestDto genreRequestDto) {
        Genre genre = new Genre();
        genre.setName(genreRequestDto.name());
        return genreRepository.save(genre);
    }

    public List<Genre> deleteGenreById(String id) {
        genreRepository.deleteById(id);
        return getAllGenres();
    }

    public Genre updateGenreById(String id, GenreRequestDto genreRequestDto) {
        Genre genre = genreRepository.findById(id).get();
        genre.setName(genreRequestDto.name());
        return genreRepository.save(genre);
    }
}
