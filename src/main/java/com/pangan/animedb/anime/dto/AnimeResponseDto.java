package com.pangan.animedb.anime.dto;

import com.pangan.animedb.genre.dao.Genre;
import com.pangan.animedb.tag.dao.Tag;
import lombok.Builder;

import java.util.List;

@Builder
public record AnimeResponseDto(
        Long id,

        String title,
        Float rating,
        String synopsis,

        String japaneseTitle,
        String japaneseTitleHiragana,
        String japaneseSynopsis,

        String dateAired,
        String dateFinished,

        Integer episodes,
        String studio,
        String duration,
        String imageUrl,

        List<Genre> genreList,
        List<Tag> tagList
) {
}
