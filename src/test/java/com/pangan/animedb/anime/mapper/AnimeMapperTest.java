package com.pangan.animedb.anime.mapper;

import com.pangan.animedb.anime.dao.Anime;
import com.pangan.animedb.anime.dto.AnimeDefaultDetailDto;
import com.pangan.animedb.anime.dto.AnimeImageDetailDto;
import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class AnimeMapperTest {

    private AnimeRequestDto animeRequestDto;
    private Anime anime;
    private List<Anime> animeList = new ArrayList<>();

    @BeforeEach
    public void init() {
        animeRequestDto = AnimeRequestDto.builder()
                .title("Title")
                .rating(4.5F)
                .synopsis("Synopsis")

                .japaneseTitle("日本語のタイトル")
                .japaneseTitleHiragana("にほんごのタイトル")
                .japaneseSynopsis("日本語のシノプシス")

                .dateAired(new Date().toString())
                .dateFinished(new Date().toString())

                .episodes(52)
                .duration("24 minutes")
                .imageUrl("https://fav.ico/")
                .build();

        anime = Anime.builder()
                .id(1_000_000L)

                .title("Title")
                .rating(4.5F)
                .synopsis("Synopsis")

                .japaneseTitle("日本語のタイトル")
                .japaneseTitleHiragana("にほんごのタイトル")
                .japaneseSynopsis("日本語のシノプシス")

                .dateAired(new Date().toString())
                .dateFinished(new Date().toString())

                .episodes(52)
                .duration("24 minutes")
                .imageUrl("https://fav.ico/")
                .build();

        for (int i = 0; i < 5; i++) {
            animeList.add(anime);
        }
    }

    @Test
    public void animeMapper_mapRequestToAnime_ReturnsAnime() {
        var anime = AnimeMapper.mapRequestToAnime(animeRequestDto);

        Assertions.assertThat(anime.getClass()).isEqualTo(Anime.class);
        Assertions.assertThat(anime.getTitle()).isEqualTo(animeRequestDto.title());
    }

    @Test
    public void animeMapper_mapAnimeToResponse_ReturnsAnimeResponseDto() {
        var mappedAnimeToResponse = AnimeMapper.mapAnimeToResponse(anime);

        Assertions.assertThat(mappedAnimeToResponse.getClass()).isEqualTo(AnimeResponseDto.class);
        Assertions.assertThat(mappedAnimeToResponse.id()).isEqualTo(anime.getId());
    }

    @Test
    public void animeMapper_mapAnimeToImageDetail_ReturnsAnimeImageDetailDto() {
        var mappedAnimeToImageDetail = AnimeMapper.mapAnimeToImageDetail(anime);

        Assertions.assertThat(mappedAnimeToImageDetail.getClass()).isEqualTo(AnimeImageDetailDto.class);
        Assertions.assertThat(mappedAnimeToImageDetail.id()).isEqualTo(anime.getId());
    }

    @Test
    public void animeMapper_mapAnimeStreamToResponseList_ReturnsAnimeResponseDtoList() {
        var mappedAnimeStreamToResponseList = AnimeMapper.mapAnimeStreamToDefaultDetailsList(animeList.stream());

        Assertions.assertThat(mappedAnimeStreamToResponseList).isNotNull();
        Assertions.assertThat(mappedAnimeStreamToResponseList).isNotEmpty();
        Assertions.assertThat(mappedAnimeStreamToResponseList.get(0)).isNotNull();
        Assertions.assertThat(mappedAnimeStreamToResponseList.get(0).getClass()).isEqualTo(AnimeDefaultDetailDto.class);
    }
}