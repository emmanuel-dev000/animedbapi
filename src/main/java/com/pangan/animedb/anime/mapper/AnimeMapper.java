package com.pangan.animedb.anime.mapper;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.dao.Anime;

public class AnimeMapper {

    public static Anime mapRequestToAnime(AnimeRequestDto animeRequestDto) {
        return mapRequestToAnime(animeRequestDto, new Anime());
    }

    public static Anime mapRequestToAnime(AnimeRequestDto animeRequestDto, Anime anime) {
        Anime mappedRequestToAnime = Anime.builder()
                .id(anime.getId())

                .title(animeRequestDto.title())
                .rating(animeRequestDto.rating())
                .synopsis(animeRequestDto.synopsis())

                .japaneseTitle(animeRequestDto.japaneseTitle())
                .japaneseTitleHiragana(animeRequestDto.japaneseTitleHiragana())
                .japaneseSynopsis(animeRequestDto.japaneseSynopsis())

                .dateAired(animeRequestDto.dateAired())
                .dateFinished(animeRequestDto.dateFinished())

                .episodes(animeRequestDto.episodes())
                .studio(animeRequestDto.studio())
                .duration(animeRequestDto.duration())
                .imageUrl(animeRequestDto.imageUrl())
                .build();

        return mappedRequestToAnime;
    }

    public static AnimeResponseDto mapAnimeToResponse(Anime anime) {
        AnimeResponseDto mappedAnimeToResponse = AnimeResponseDto.builder()
                .id(anime.getId())

                .title(anime.getTitle())
                .rating(anime.getRating())
                .synopsis(anime.getSynopsis())

                .japaneseTitle(anime.getJapaneseTitle())
                .japaneseTitleHiragana(anime.getJapaneseTitleHiragana())
                .japaneseSynopsis(anime.getJapaneseSynopsis())

                .dateAired(anime.getDateAired())
                .dateFinished(anime.getDateFinished())

                .episodes(anime.getEpisodes())
                .studio(anime.getStudio())
                .duration(anime.getDuration())
                .imageUrl(anime.getImageUrl())

                .genreList(anime.getGenreList())
                .tagList(anime.getTagList())
                .build();

        return  mappedAnimeToResponse;
    }
}