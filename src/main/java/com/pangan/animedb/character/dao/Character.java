package com.pangan.animedb.character.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document
public class Character {
    @Id
    private String id;
    private String name;
    private String background;
    private String animeId;
    private String japaneseName;
    private String japaneseBackground;
}