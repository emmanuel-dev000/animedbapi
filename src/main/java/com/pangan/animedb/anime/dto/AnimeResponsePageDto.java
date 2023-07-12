package com.pangan.animedb.anime.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AnimeResponsePageDto(
        Integer pageNumber,
        Integer pageSize,
        Long totalElements,
        Integer totalPages,
        List<AnimeResponseDto> animeContentList
) {
}
