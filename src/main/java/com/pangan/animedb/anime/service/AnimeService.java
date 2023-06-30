package com.pangan.animedb.anime.service;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.model.Anime;
import com.pangan.animedb.anime.repository.AnimeRepository;
import com.pangan.animedb.anime.mapper.AnimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public AnimeResponseDto addAnime(AnimeRequestDto animeRequestDto) {
        Anime newAnime = AnimeMapper.mapRequestToAnime(animeRequestDto);
        Anime savedAnime = animeRepository.save(newAnime);
        return AnimeMapper.mapAnimeToResponse(savedAnime);
    }
}
