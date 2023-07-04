package com.pangan.animedb.character.dto;

public record CharacterRequestDto(
        String name,
        String background,
        String animeId,
        String japaneseName,
        String japaneseBackground
) {
}
