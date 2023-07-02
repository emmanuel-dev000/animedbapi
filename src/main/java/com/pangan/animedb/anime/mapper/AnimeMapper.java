package com.pangan.animedb.anime.mapper;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.model.Anime;

public class AnimeMapper {

    public static Anime mapRequestToAnime(AnimeRequestDto animeRequestDto) {
        Anime anime = new Anime();
        anime.setName(animeRequestDto.name());
        return anime;
    }

    public static Anime mapRequestToAnime(AnimeRequestDto animeRequestDto, Anime anime) {
        anime.setName(animeRequestDto.name());
        return anime;
    }

    public static AnimeResponseDto mapAnimeToResponse(Anime anime) {
        return new AnimeResponseDto(
                anime.getId(),
                anime.getName()
        );
    }
}
