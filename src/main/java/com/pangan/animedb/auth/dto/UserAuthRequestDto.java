package com.pangan.animedb.auth.dto;

public record UserAuthRequestDto(
        String username,
        String password
) {
}
