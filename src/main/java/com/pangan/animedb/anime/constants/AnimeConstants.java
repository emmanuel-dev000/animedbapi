package com.pangan.animedb.anime.constants;

import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.model.Anime;

import java.util.List;

public class AnimeConstants {

    public static final String NO_ANIME_FOUND_MESSAGE = "No anime found.";
    public static final String ANIME_DELETED_MESSAGE = "Anime removed from the database.";

    public static final AnimeResponseDto NO_ANIME_FOUND_RESPONSE
            = new AnimeResponseDto(NO_ANIME_FOUND_MESSAGE, NO_ANIME_FOUND_MESSAGE);


    public static final List<AnimeResponseDto> NO_ANIME_LIST_FOUND_RESPONSE
            = List.of(NO_ANIME_FOUND_RESPONSE);

    public static final AnimeResponseDto ANIME_DELETE_RESPONSE
            = new AnimeResponseDto(ANIME_DELETED_MESSAGE, ANIME_DELETED_MESSAGE);
}
