package com.pangan.animedb.anime.dto;

import com.pangan.animedb.genre.dao.Genre;
import com.pangan.animedb.tag.dao.Tag;

import java.util.List;

public record AnimeRequestDto(
        String name,
        String background,
        Integer episodes,
        String status,
        String season,
        String studio,
        String startDate,
        String endDate,
        String imageUrl,
        String japaneseName,
        String japaneseBackground,
        List<Genre> genreList,
        List<Tag> tagList
) {
}