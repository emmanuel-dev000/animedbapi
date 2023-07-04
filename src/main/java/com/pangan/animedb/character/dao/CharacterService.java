package com.pangan.animedb.character.dao;

import com.pangan.animedb.character.dto.CharacterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> getAllCharacters() {
        if (characterRepository.findAll().isEmpty()) {
            // throw an exception
        }

        return characterRepository.findAll();
    }

    public Character getCharacterById(String id) {
        if (!characterRepository.existsById(id) || characterRepository.findById(id).isEmpty()) {
            // throw an exception
        }

        return characterRepository.findById(id).get();
    }

    public Character addCharacter(CharacterRequestDto characterRequestDto) {
        Character character = new Character();
        character.setName(characterRequestDto.name());
        character.setBackground(characterRequestDto.background());
        character.setAnimeId(characterRequestDto.animeId());
        character.setJapaneseName(characterRequestDto.japaneseName());
        character.setJapaneseBackground(characterRequestDto.japaneseBackground());

        return characterRepository.save(character);
    }

    public List<Character> deleteCharacterById(String id) {
        if (!characterRepository.existsById(id) || characterRepository.findById(id).isEmpty()) {
            // throw an exception
        }

        characterRepository.deleteById(id);
        return getAllCharacters();
    }

    public Character updateCharacterById(String id, CharacterRequestDto characterRequestDto) {
        if (!characterRepository.existsById(id) || characterRepository.findById(id).isEmpty()) {
            // throw an exception
        }

        // TODO: Check all fields of characteRequestDto

        Character character = characterRepository.findById(id).get();
        character.setName(characterRequestDto.name());
        character.setBackground(characterRequestDto.background());
        character.setAnimeId(characterRequestDto.animeId());
        character.setJapaneseName(characterRequestDto.japaneseName());
        character.setJapaneseBackground(characterRequestDto.background());

        return characterRepository.save(character);
    }
}
