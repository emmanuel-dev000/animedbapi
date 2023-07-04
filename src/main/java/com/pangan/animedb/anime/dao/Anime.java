package com.pangan.animedb.anime.dao;

import com.pangan.animedb.genre.dao.Genre;
import com.pangan.animedb.tag.dao.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Document
public class Anime {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
    private String background;
    private Integer episodes;
    private String status;
    private String season;
    private String studio;
    private String startDate;
    private String endDate;
    private String imageUrl;
    private String japaneseName;
    private String japaneseBackground;

//    @DBRef
//    private List<Character> characterList;

    @DBRef
    private List<Genre> genreList = new ArrayList<>();
    @DBRef
    private List<Tag> tagList = new ArrayList<>();
}
