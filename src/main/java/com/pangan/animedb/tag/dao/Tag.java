package com.pangan.animedb.tag.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Tag {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
}
