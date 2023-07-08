package com.pangan.animedb.anime.mapper;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.dao.Anime;

public class AnimeMapper {

    public static Anime mapRequestToAnime(AnimeRequestDto animeRequestDto) {
        return mapRequestToAnime(animeRequestDto, new Anime());
    }

    public static Anime mapRequestToAnime(AnimeRequestDto animeRequestDto, Anime anime) {
        anime.setTitle(animeRequestDto.name());
        anime.setSynopsis(animeRequestDto.background());
        anime.setEpisodes(animeRequestDto.episodes());
        anime.setSeason(animeRequestDto.season());
        anime.setStudio(animeRequestDto.studio());
        anime.setStatus(animeRequestDto.status());
        anime.setStartDate(animeRequestDto.startDate());
        anime.setEndDate(animeRequestDto.endDate());
        anime.setImageUrl(animeRequestDto.imageUrl());
        anime.setJapaneseTitle(animeRequestDto.japaneseName());
        anime.setJapaneseSynopsis(animeRequestDto.japaneseBackground());
        anime.setGenreList(animeRequestDto.genreList());
        anime.setTagList(animeRequestDto.tagList());
        anime.setCharacterList(animeRequestDto.characterList());
        return anime;
    }

    public static AnimeResponseDto mapAnimeToResponse(Anime anime) {
        return new AnimeResponseDto(
                anime.getId(),
                anime.getTitle(),
                anime.getSynopsis(),
                anime.getEpisodes(),
                anime.getStatus(),
                anime.getSeason(),
                anime.getStudio(),
                anime.getStartDate(),
                anime.getEndDate(),
                anime.getImageUrl(),
                anime.getJapaneseTitle(),
                anime.getJapaneseSynopsis(),
                anime.getGenreList(),
                anime.getTagList(),
                anime.getCharacterList()
        );
    }
}