package com.pangan.animedb.anime.dto;

import lombok.Builder;

@Builder
public record AnimeRequestDto(
        String title,
        Float rating,
        String synopsis,

        String japaneseTitle,
        String japaneseTitleHirgana,
        String japaneseSynopsis,

        String dateAired,
        String dateFinished,

        Integer episodes,
        String studio,
        String duration,
        String imageUrl
) {
}