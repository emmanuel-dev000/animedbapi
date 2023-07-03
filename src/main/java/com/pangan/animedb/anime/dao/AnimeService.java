package com.pangan.animedb.anime.dao;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.mapper.AnimeMapper;
import com.pangan.animedb.anime.exception.IncompleteAnimeFieldsException;
import com.pangan.animedb.anime.exception.NoAnimeFoundException;
import com.pangan.animedb.genre.dao.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<AnimeResponseDto> getAllAnime() throws NoAnimeFoundException {
        List<Anime> animeList = animeRepository.findAll();
        if (animeList.isEmpty()) {
            throw new NoAnimeFoundException();
        }

        return animeList.stream()
                    .map(anime -> AnimeMapper.mapAnimeToResponse(anime))
                    .collect(Collectors.toList());
    }

    public AnimeResponseDto getAnimeById(String id) throws NoAnimeFoundException {
        Optional<Anime> optionalAnime = animeRepository.findById(id);
        if (optionalAnime.isEmpty()) {
            throw new NoAnimeFoundException();
        }

        return AnimeMapper.mapAnimeToResponse(optionalAnime.get());
    }

    public AnimeResponseDto addAnime(AnimeRequestDto animeRequestDto) throws IncompleteAnimeFieldsException {
        if (isIncompleteAnimeFields(animeRequestDto)) {
            throw new IncompleteAnimeFieldsException();
        }

        Anime addAnime = AnimeMapper.mapRequestToAnime(animeRequestDto);
        Anime savedAnime = animeRepository.save(addAnime);
        return AnimeMapper.mapAnimeToResponse(savedAnime);
    }

    public List<AnimeResponseDto> deleteAnimeById(String id) throws NoAnimeFoundException {
        if (animeRepository.findById(id).isEmpty()) {
            throw new NoAnimeFoundException();
        }

        animeRepository.deleteById(id);
        return getAllAnime();
    }

    public AnimeResponseDto updateAnimeById(String id, AnimeRequestDto animeRequestDto) throws IncompleteAnimeFieldsException, NoAnimeFoundException {
        if (isIncompleteAnimeFields(animeRequestDto)) {
            throw new IncompleteAnimeFieldsException();
        }

        Optional<Anime> optionalAnime = animeRepository.findById(id);
        if (optionalAnime.isEmpty()) {
            throw new NoAnimeFoundException();
        }

        Anime anime = AnimeMapper.mapRequestToAnime(animeRequestDto, optionalAnime.get());
        Anime updatedAnime = animeRepository.save(anime);
        return AnimeMapper.mapAnimeToResponse(updatedAnime);
    }

    private static boolean isIncompleteAnimeFields(AnimeRequestDto animeRequestDto) {
        return !StringUtils.hasText(animeRequestDto.name())
                || !StringUtils.hasText(animeRequestDto.background())
                || !StringUtils.hasText(animeRequestDto.season())
                || !StringUtils.hasText(animeRequestDto.status())
                || !StringUtils.hasText(animeRequestDto.studio())
                || !StringUtils.hasText(animeRequestDto.startDate())
                || !StringUtils.hasText(animeRequestDto.endDate())
                || animeRequestDto.episodes() <= -1;
    }

    public AnimeResponseDto addGenreListToAnimeById(String id, List<Genre> genreList) {
        if (genreList.isEmpty()) {
            // throw an exception here.
        }

        if (!animeRepository.existsById(id)) {
            // throw an exception here.
        }

        Optional<Anime> optionalAnime = animeRepository.findById(id);
        if (optionalAnime.isEmpty()) {
            // throw an exception here.
        }

        Anime anime = optionalAnime.get();
        anime.getGenreList().addAll(genreList);
        Anime updatedAnime = animeRepository.save(anime);
        return AnimeMapper.mapAnimeToResponse(updatedAnime);
    }
}
