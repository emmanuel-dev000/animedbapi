package com.pangan.animedb.genre.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Genre {
    @Id
    private String id;
    private String name;
}
