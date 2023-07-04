package com.pangan.animedb.anime.dao;

import com.pangan.animedb.anime.dto.AnimeRequestDto;
import com.pangan.animedb.anime.dto.AnimeResponseDto;
import com.pangan.animedb.anime.mapper.AnimeMapper;
import com.pangan.animedb.anime.exception.IncompleteAnimeFieldsException;
import com.pangan.animedb.anime.exception.AnimeNotFoundException;
import com.pangan.animedb.character.dao.Character;
import com.pangan.animedb.character.exception.CharacterNotFoundException;
import com.pangan.animedb.character.exception.IncompleteCharacterFieldsException;
import com.pangan.animedb.genre.dao.Genre;
import com.pangan.animedb.genre.exception.GenreNotFoundException;
import com.pangan.animedb.tag.dao.Tag;
import com.pangan.animedb.tag.exception.IncompleteTagFieldsException;
import com.pangan.animedb.tag.exception.TagNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<AnimeResponseDto> getAllAnime() throws AnimeNotFoundException {
        List<Anime> animeList = animeRepository.findAll();
        if (animeList.isEmpty()) {
            throw new AnimeNotFoundException();
        }

        return animeList.stream()
                    .map(AnimeMapper::mapAnimeToResponse)
                    .collect(Collectors.toList());
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

    public List<AnimeResponseDto> deleteAnimeById(String id) throws AnimeNotFoundException {
        if (animeRepository.findById(id).isEmpty()) {
            throw new AnimeNotFoundException();
        }

        animeRepository.deleteById(id);
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

    private static boolean isIncompleteAnimeFields(AnimeRequestDto animeRequestDto) {
        return !StringUtils.hasText(animeRequestDto.name())
                || !StringUtils.hasText(animeRequestDto.background())
                || !StringUtils.hasText(animeRequestDto.season())
                || !StringUtils.hasText(animeRequestDto.status())
                || !StringUtils.hasText(animeRequestDto.studio())
                || !StringUtils.hasText(animeRequestDto.startDate())
                || !StringUtils.hasText(animeRequestDto.endDate())
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
            throw new IncompleteTagFieldsException();
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

    public AnimeResponseDto addCharacterListToAnimeById(String id, List<Character> characterList) throws AnimeNotFoundException, IncompleteTagFieldsException {
        if (!animeRepository.existsById(id) || animeRepository.findById(id).isEmpty()) {
            throw new AnimeNotFoundException();
        }

        if (isIncompleteCharacterListFields(characterList)) {
            throw new IncompleteCharacterFieldsException();
        }

        Anime anime = animeRepository.findById(id).get();
        characterList.stream()
                .filter(character -> !anime.getCharacterList().contains(character))
                .forEach(anime.getCharacterList()::add);
        Anime savedAnime = animeRepository.save(anime);
        return  AnimeMapper.mapAnimeToResponse(savedAnime);
    }

    private static boolean isIncompleteCharacterListFields(List<Character> characterList) {
        return characterList.stream()
                    .anyMatch(character -> !StringUtils.hasText(character.getName())
                        || !StringUtils.hasText(character.getBackground())
                        || !StringUtils.hasText(character.getAnimeId())
                        || !StringUtils.hasText(character.getJapaneseName())
                        || !StringUtils.hasText(character.getJapaneseBackground()));
    }

    public AnimeResponseDto deleteCharacterInAnimeById(String id, Character character) throws AnimeNotFoundException, CharacterNotFoundException {
        if (!animeRepository.existsById(id) || animeRepository.findById(id).isEmpty()) {
            throw new AnimeNotFoundException();
        }

        Anime anime = animeRepository.findById(id).get();
        if (!anime.getCharacterList().remove(character)) {
             throw new CharacterNotFoundException();
        }

        Anime savedAnime = animeRepository.save(anime);
        return AnimeMapper.mapAnimeToResponse(savedAnime);
    }
}
