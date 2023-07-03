package com.pangan.animedb.anime.mapper;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.dao.Anime;

public class AnimeMapper {

    public static Anime mapRequestToAnime(AnimeRequestDto animeRequestDto) {
        return mapRequestToAnime(animeRequestDto, new Anime());
    }

    public static Anime mapRequestToAnime(AnimeRequestDto animeRequestDto, Anime anime) {
        anime.setName(animeRequestDto.name());
        anime.setBackground(animeRequestDto.background());
        anime.setEpisodes(animeRequestDto.episodes());
        anime.setSeason(animeRequestDto.season());
        anime.setStudio(animeRequestDto.studio());
        anime.setStatus(animeRequestDto.status());
        anime.setStartDate(animeRequestDto.startDate());
        anime.setEndDate(animeRequestDto.endDate());
        anime.setImageUrl(animeRequestDto.imageUrl());
        anime.setJapaneseName(animeRequestDto.japaneseName());
        anime.setJapaneseBackground(animeRequestDto.japaneseBackground());
        anime.setGenreList(animeRequestDto.genreList());
        return anime;
    }

    public static AnimeResponseDto mapAnimeToResponse(Anime anime) {
        return new AnimeResponseDto(
                anime.getId(),
                anime.getName(),
                anime.getBackground(),
                anime.getEpisodes(),
                anime.getStatus(),
                anime.getSeason(),
                anime.getStudio(),
                anime.getStartDate(),
                anime.getEndDate(),
                anime.getImageUrl(),
                anime.getJapaneseName(),
                anime.getJapaneseBackground(),
                anime.getGenreList()
        );
    }
}