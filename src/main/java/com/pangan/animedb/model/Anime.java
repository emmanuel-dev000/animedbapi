package com.pangan.animedb.model;

import org.springframework.data.annotation.Id;

public class Anime {
    @Id
    private String id;
    private String name;

    public Anime() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
