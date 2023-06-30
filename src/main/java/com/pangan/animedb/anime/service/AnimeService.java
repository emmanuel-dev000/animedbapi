package com.pangan.animedb.anime.service;

import com.pangan.animedb.anime.constants.AnimeConstants;
import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.model.Anime;
import com.pangan.animedb.anime.repository.AnimeRepository;
import com.pangan.animedb.anime.mapper.AnimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<AnimeResponseDto> getAllAnime() {
        try {
            List<Anime> animeList = animeRepository.findAll();
            if (animeList.isEmpty())
                return AnimeConstants.NO_ANIME_LIST_FOUND_RESPONSE;

            return animeList.stream()
                    .map(anime -> AnimeMapper.mapAnimeToResponse(anime))
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            return AnimeConstants.NO_ANIME_LIST_FOUND_RESPONSE;
        }
    }

    public AnimeResponseDto getAnimeById(String id) {
        try {
            Optional<Anime> anime = animeRepository.findById(id);
            if (anime.isEmpty())
                return AnimeConstants.NO_ANIME_FOUND_RESPONSE;

            return AnimeMapper.mapAnimeToResponse(anime.get());
        } catch (RuntimeException e) {
            return AnimeConstants.NO_ANIME_FOUND_RESPONSE;
        }
    }

    public AnimeResponseDto addAnime(AnimeRequestDto animeRequestDto) {
        try {
            Anime addAnime = AnimeMapper.mapRequestToAnime(animeRequestDto);
            Anime savedAnime = animeRepository.save(addAnime);
            return AnimeMapper.mapAnimeToResponse(savedAnime);

        } catch (RuntimeException e) {
            return AnimeConstants.NO_ANIME_FOUND_RESPONSE;
        }
    }

    public List<AnimeResponseDto> deleteAnimeById(String id) {
        if (animeRepository.findById(id).isEmpty()) {
            return AnimeConstants.NO_ANIME_LIST_FOUND_RESPONSE;
        }

        animeRepository.deleteById(id);
        return getAllAnime();
    }
}
