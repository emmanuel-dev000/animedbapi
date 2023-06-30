package com.pangan.animedb.mapper;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.model.Anime;

public class AnimeMapper {

    public static AnimeResponseDto mapAnimeToAnimeResponseDto(Anime anime) {
        return new AnimeResponseDto(
                anime.getId(),
                anime.getName()
        );
    }

    public static Anime mapAnimeRequestDtoToAnime(AnimeRequestDto animeRequestDto) {
        Anime anime = new Anime();
        anime.setName(animeRequestDto.name());
        return anime;
    }
}
