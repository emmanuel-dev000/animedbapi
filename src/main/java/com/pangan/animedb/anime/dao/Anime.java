package com.pangan.animedb.anime.dao;

import com.pangan.animedb.genre.dao.Genre;
import com.pangan.animedb.tag.dao.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Anime {
    @Id
    private String id;

    @Indexed(unique = true)
    private String title;
    private Float rating;
    private String synopsis;

    private String japaneseTitle;
    private String japaneseTitleHiragana;
    private String japaneseSynopsis;

    private String dateAired;
    private String dateFinished;

    private Integer episodes;
    private String studio;
    private String duration;
    private String imageUrl;

    @DBRef
    private List<Genre> genreList = new ArrayList<>();

    @DBRef
    private List<Tag> tagList = new ArrayList<>();

    @CreatedDate
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

}
