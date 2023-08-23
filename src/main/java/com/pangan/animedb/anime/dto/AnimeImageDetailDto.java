package com.pangan.animedb.anime.dto;

import lombok.Builder;

@Builder
public record AnimeImageDetailDto(
        Long id,
        String title,
        String imageUrl
) {
}
