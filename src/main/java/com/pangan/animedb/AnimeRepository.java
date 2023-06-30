package com.pangan.animedb;

import com.pangan.animedb.model.Anime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AnimeRepository extends MongoRepository<Anime, String> {
    Optional<Anime> findAnimeByName(String name);
}
