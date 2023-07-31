package com.pangan.animedb.anime.dao;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.dto.AnimePageDto;
import com.pangan.animedb.anime.mapper.AnimeMapper;
import com.pangan.animedb.anime.exception.IncompleteAnimeFieldsException;
import com.pangan.animedb.anime.exception.AnimeNotFoundException;
import com.pangan.animedb.genre.dao.Genre;
import com.pangan.animedb.genre.exception.GenreNotFoundException;
import com.pangan.animedb.tag.dao.Tag;
import com.pangan.animedb.tag.exception.IncompleteTagFieldsException;
import com.pangan.animedb.tag.exception.TagNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {

    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 5;
    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Deprecated
    public List<AnimeResponseDto> getAllAnime() throws AnimeNotFoundException {
        List<Anime> animeList = animeRepository.findAll();
        if (animeList.isEmpty()) {
            throw new AnimeNotFoundException();
        }

        return AnimeMapper.mapAnimeStreamToResponseList(animeList.stream());
    }

    public AnimePageDto getAllAnimeInPage(Integer pageNumber , Integer pageSize) throws AnimeNotFoundException {
        if (pageNumber.describeConstable().isEmpty() || pageSize.describeConstable().isEmpty()) {
            pageNumber = DEFAULT_PAGE_NUMBER;
            pageSize = DEFAULT_PAGE_SIZE;
        }

        if (animeRepository.findAll().isEmpty()) {
            throw new AnimeNotFoundException();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Anime> animePage = animeRepository.findAll(pageable);
        return AnimePageDto.builder()
                .pageNumber(animePage.getPageable().getPageNumber())
                .pageSize(animePage.getPageable().getPageSize())
                .totalPages(animePage.getTotalPages())
                .totalElements(animePage.getTotalElements())
                .animeContentList(AnimeMapper.mapAnimeStreamToDefaultDetailsList(animePage.stream()))
                .build();
    }

    public AnimeResponseDto getAnimeById(String id) throws AnimeNotFoundException {
        Optional<Anime> optionalAnime = animeRepository.findById(id);
        if (optionalAnime.isEmpty()) {
            throw new AnimeNotFoundException();
        }

        return AnimeMapper.mapAnimeToResponse(optionalAnime.get());
    }

    public AnimeResponseDto addAnime(AnimeRequestDto animeRequestDto) throws IncompleteAnimeFieldsException {
        if (isIncompleteAnimeFields(animeRequestDto)) {
            throw new IncompleteAnimeFieldsException();
        }

        Anime addAnime = AnimeMapper.mapRequestToAnime(animeRequestDto);
        Anime savedAnime = animeRepository.save(addAnime);
        return AnimeMapper.mapAnimeToResponse(savedAnime);
    }

    public AnimePageDto deleteAnimeById(String id) throws AnimeNotFoundException {
        if (animeRepository.findById(id).isEmpty()) {
            throw new AnimeNotFoundException();
        }

        animeRepository.deleteById(id);
        return getAllAnimeInPage(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    }

    public List<AnimeResponseDto> deleteAnimeListByAnimeIdList(List<String> animeIdList) throws AnimeNotFoundException {
        if (animeIdList.isEmpty()) {
            throw new AnimeNotFoundException();
        }

        animeRepository.deleteAllById(animeIdList);

        return getAllAnime();
    }

    public AnimeResponseDto updateAnimeById(String id, AnimeRequestDto animeRequestDto) throws IncompleteAnimeFieldsException, AnimeNotFoundException {
        if (isIncompleteAnimeFields(animeRequestDto)) {
            throw new IncompleteAnimeFieldsException();
        }

        Optional<Anime> optionalAnime = animeRepository.findById(id);
        if (optionalAnime.isEmpty()) {
            throw new AnimeNotFoundException();
        }

        Anime anime = AnimeMapper.mapRequestToAnime(animeRequestDto, optionalAnime.get());
        Anime updatedAnime = animeRepository.save(anime);
        return AnimeMapper.mapAnimeToResponse(updatedAnime);
    }

    public static boolean isIncompleteAnimeFields(AnimeRequestDto animeRequestDto) {
        return !StringUtils.hasText(animeRequestDto.title())
                || !StringUtils.hasText(animeRequestDto.synopsis())
                || !StringUtils.hasText(animeRequestDto.dateAired())
                || !StringUtils.hasText(animeRequestDto.dateFinished())
                || !StringUtils.hasText(animeRequestDto.studio())
                || !StringUtils.hasText(animeRequestDto.duration())
                || !StringUtils.hasText(animeRequestDto.imageUrl())
                || animeRequestDto.rating() <= -1
                || animeRequestDto.episodes() <= -1;
    }

    public AnimeResponseDto addGenreListToAnimeById(String id, List<Genre> genreList) throws AnimeNotFoundException, GenreNotFoundException {
        if (genreList.isEmpty()) {
            throw new GenreNotFoundException();
        }

        if (!animeRepository.existsById(id)) {
            throw new AnimeNotFoundException();
        }

        Optional<Anime> optionalAnime = animeRepository.findById(id);
        if (optionalAnime.isEmpty()) {
            throw new AnimeNotFoundException();
        }

        Anime anime = optionalAnime.get();
        anime.getGenreList().addAll(genreList);
        Anime updatedAnime = animeRepository.save(anime);
        return AnimeMapper.mapAnimeToResponse(updatedAnime);
    }

    public AnimeResponseDto deleteGenreInAnimeById(String id, Genre genre) throws AnimeNotFoundException, GenreNotFoundException {
        Optional<Anime> optionalAnime = animeRepository.findById(id);
        if (optionalAnime.isEmpty()) {
            throw new AnimeNotFoundException();
        }

        if (!StringUtils.hasText(genre.getId()) || !StringUtils.hasText(genre.getName())) {
            throw new GenreNotFoundException();
        }

        Anime anime = optionalAnime.get();
        if (!anime.getGenreList().contains(genre)) {
            throw new GenreNotFoundException();
        }

        anime.getGenreList().remove(genre);
        Anime updatedAnime = animeRepository.save(anime);
        return AnimeMapper.mapAnimeToResponse(updatedAnime);
    }

    public AnimeResponseDto addTagListInAnimeById(String id, List<Tag> tagList) throws TagNotFoundException, IncompleteTagFieldsException {
        if (tagList.isEmpty()) {
            throw new TagNotFoundException();
        }

        if (!animeRepository.existsById(id) || animeRepository.findById(id).isEmpty()) {
            throw new TagNotFoundException();
        }

        Anime anime = animeRepository.findById(id).get();
        tagList.stream()
                .filter(tag -> !anime.getTagList().contains(tag))
                .forEach(anime.getTagList()::add);
        animeRepository.save(anime);
        return AnimeMapper.mapAnimeToResponse(anime);
    }

    public AnimeResponseDto deleteTagInAnimeById(String id, Tag tag) throws AnimeNotFoundException {
        if (!animeRepository.existsById(id) || animeRepository.findById(id).isEmpty()) {
            throw new AnimeNotFoundException();
        }

        Anime anime =  animeRepository.findById(id).get();
        if (!anime.getTagList().remove(tag)) {
            throw new TagNotFoundException();
        }

        Anime savedAnime = animeRepository.save(anime);
        return AnimeMapper.mapAnimeToResponse(savedAnime);
    }
}
