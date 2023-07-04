package com.pangan.animedb.genre.dao;

import com.pangan.animedb.genre.dto.GenreRequestDto;
import com.pangan.animedb.genre.exception.GenreNotFoundException;
import com.pangan.animedb.genre.exception.IncompleteGenreFieldsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() throws GenreNotFoundException {
        if (genreRepository.findAll().isEmpty()) {
            throw new GenreNotFoundException();
        }

        return genreRepository.findAll();
    }

    public Genre getGenreById(String id) throws GenreNotFoundException {
        if (!genreRepository.existsById(id) || genreRepository.findById(id).isEmpty()) {
            throw new GenreNotFoundException();
        }

        return genreRepository.findById(id).get();
    }

    public Genre addGenre(GenreRequestDto genreRequestDto) throws IncompleteGenreFieldsException {
        if (!StringUtils.hasText(genreRequestDto.name())) {
            throw new IncompleteGenreFieldsException();
        }

        Genre genre = new Genre();
        genre.setName(genreRequestDto.name());
        return genreRepository.save(genre);
    }

    public List<Genre> deleteGenreById(String id) throws GenreNotFoundException {
        if (!genreRepository.existsById(id)) {
            throw new GenreNotFoundException();
        }

        genreRepository.deleteById(id);
        return getAllGenres();
    }

    public Genre updateGenreById(String id, GenreRequestDto genreRequestDto) throws GenreNotFoundException, IncompleteGenreFieldsException {
        if (!StringUtils.hasText(genreRequestDto.name())) {
            throw new IncompleteGenreFieldsException();
        }

        if (!genreRepository.existsById(id) || genreRepository.findById(id).isEmpty()) {
            throw new GenreNotFoundException();
        }

        Genre genre = genreRepository.findById(id).get();
        genre.setName(genreRequestDto.name());
        return genreRepository.save(genre);
    }
}
