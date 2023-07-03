package com.pangan.animedb.anime.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AnimeRepository extends MongoRepository<Anime, String> {
    Optional<Anime> findAnimeByName(String name);
}
