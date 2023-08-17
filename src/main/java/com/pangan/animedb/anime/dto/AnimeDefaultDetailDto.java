package com.pangan.animedb.anime.dto;

import lombok.Builder;

@Builder
public record AnimeDefaultDetailDto(
        String id,
        String title
) {
}
