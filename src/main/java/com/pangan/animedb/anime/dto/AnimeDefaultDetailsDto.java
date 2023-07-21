package com.pangan.animedb.anime.dto;

import lombok.Builder;

@Builder
public record AnimeDefaultDetailsDto(
        String id,
        String title,
        String japaneseTitle,
        String studio,
        Integer episodes,
        String duration

) {
}
