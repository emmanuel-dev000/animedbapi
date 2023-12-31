package com.pangan.animedb.anime.mapper;

import com.pangan.animedb.anime.dao.Anime;
import com.pangan.animedb.anime.dto.AnimeDefaultDetailDto;
import com.pangan.animedb.anime.dto.AnimeImageDetailDto;
import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static AnimeImageDetailDto mapAnimeToImageDetail(Anime anime) {
        AnimeImageDetailDto mappedAnimeToImageDetail = AnimeImageDetailDto.builder()
                .id(anime.getId())
                .title(anime.getTitle())
                .imageUrl(anime.getImageUrl())
                .build();

        return mappedAnimeToImageDetail;
    }

    @Deprecated
    public static List<AnimeResponseDto> mapAnimeStreamToResponseList(Stream<Anime> animeStream) {
        return animeStream
                .map(anime -> mapAnimeToResponse(anime))
                .collect(Collectors.toList());
    }

    public static List<AnimeDefaultDetailDto> mapAnimeStreamToDefaultDetailsList(Stream<Anime> animeStream) {
        return animeStream
                .map(anime -> mapAnimeToAnimeDefaultDetails(anime))
                .collect(Collectors.toList());
    }

    public static AnimeDefaultDetailDto mapAnimeToAnimeDefaultDetails(Anime anime) {
        return AnimeDefaultDetailDto.builder()
                .id(anime.getId())
                .title(anime.getTitle())
                .build();
    }
}