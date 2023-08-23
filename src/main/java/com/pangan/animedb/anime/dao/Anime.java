package com.pangan.animedb.anime.dao;

import com.pangan.animedb.genre.dao.Genre;
import com.pangan.animedb.tag.dao.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

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

    @ManyToMany
    @JoinTable(
            name = "anime_genre",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genreList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "anime_tag",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tagList = new ArrayList<>();

    @CreatedDate
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

}
