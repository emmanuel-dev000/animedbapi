package com.pangan.animedb.anime.dao;

import com.pangan.animedb.tag.dao.Tag;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class AnimeServiceTest {

    public static final Tag TAG_1 = new Tag();
    public static final Tag TAG_2 = new Tag();
    public static final Tag FALSE_TAG = new Tag();
    private Anime anime;
    private List<Tag> tagList = new ArrayList<>();

    @BeforeEach
    public void init() {
        TAG_1.setId(1L);
        TAG_1.setName("Tag 1");

        TAG_2.setId(2L);
        TAG_2.setName("Tag 2");

        tagList = List.of(TAG_1, TAG_2);

        anime = Anime.builder()
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

        FALSE_TAG.setId(-1L);
        FALSE_TAG.setName("False Tag");
    }

    @Test
    public void tagList_UpdatesList_ReturnsUpdatedTagList() {
        anime.setTagList(tagList);

        Assertions.assertThat(anime.getTagList()).isNotEmpty();
        Assertions.assertThat(anime.getTagList().size()).isNotEqualTo(3);
        Assertions.assertThat(anime.getTagList()).doesNotContain(FALSE_TAG);
        Assertions.assertThat(anime.getTagList().get(0)).isEqualTo(TAG_1);
        Assertions.assertThat(anime.getTagList().get(1)).isEqualTo(TAG_2);
    }

    @Test
    public void tagList_DoNotIncludeAlreadyIncludedTags_ReturnOldTagList() {
        anime.setTagList(tagList);
        for (Tag tag: tagList) {
            if (anime.getTagList().contains(tag)) {
                continue;
            }

            anime.getTagList().add(tag);
        }

        Assertions.assertThat(anime.getTagList()).isNotEmpty();
        Assertions.assertThat(anime.getTagList().size()).isEqualTo(2);
        Assertions.assertThat(anime.getTagList().get(0)).isEqualTo(TAG_1);
        Assertions.assertThat(anime.getTagList().get(1)).isEqualTo(TAG_2);
    }

//    @Test
//    public void tagList_IncludeTagsNotIncluded_ReturnNewTagList() {
//        anime.setTagList(tagList);
//        List<Tag> updatedTags = new ArrayList<>(List.of(TAG_1, TAG_2, FALSE_TAG));
//
//        for (Tag tag: updatedTags) {
//            if (anime.getTagList().contains(tag)) {
//                continue;
//            }
//
//            anime.getTagList().add(tag);
//        }
//
//        Assertions.assertThat(anime.getTagList()).isNotEmpty();
//        Assertions.assertThat(anime.getTagList().size()).isEqualTo(3);
//        Assertions.assertThat(anime.getTagList().get(2)).isEqualTo(FALSE_TAG);
//        Assertions.assertThat(anime.getTagList().get(2).getId()).isEqualTo(FALSE_TAG.getId());
//        Assertions.assertThat(anime.getTagList().get(2).getName()).isEqualTo(FALSE_TAG.getName());
//    }
}