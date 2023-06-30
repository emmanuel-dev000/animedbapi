package com.pangan.animedb.anime.service;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.model.Anime;
import com.pangan.animedb.anime.repository.AnimeRepository;
import com.pangan.animedb.mapper.AnimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    // TODO: Test if this is working.
    // - Make the String into Optional<AnimeResponseDto>
    // - Make the mapper into Optional<Anime>
    public String addAnime(AnimeRequestDto animeRequestDto) {
        Anime anime = AnimeMapper.mapAnimeRequestDtoToAnime(animeRequestDto);
        animeRepository.save(anime);
        return "Add an anime";
    }
}
