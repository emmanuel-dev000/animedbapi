package com.pangan.animedb.anime.dto;

import lombok.Builder;

@Builder
public record AnimeImageDetailDto(
        String id,
        String title,
        String imageUrl
) {
}
