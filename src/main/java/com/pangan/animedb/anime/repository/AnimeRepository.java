package com.pangan.animedb.anime.repository;

import com.pangan.animedb.anime.model.Anime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AnimeRepository extends MongoRepository<Anime, String> {
    Optional<Anime> findAnimeByName(String name);
}
