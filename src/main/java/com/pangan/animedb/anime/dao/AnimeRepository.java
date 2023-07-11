package com.pangan.animedb.anime.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimeRepository extends MongoRepository<Anime, String> {
}
