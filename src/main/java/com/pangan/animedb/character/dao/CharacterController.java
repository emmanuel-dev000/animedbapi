package com.pangan.animedb.character.dao;

import com.pangan.animedb.character.dto.CharacterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<Character>> getAllCharacters() {
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable("id") String id) {
        return ResponseEntity.ok(characterService.getCharacterById(id));
    }

    @PostMapping
    public ResponseEntity<Character> addCharacter(@RequestBody CharacterRequestDto characterRequestDto) {
        return ResponseEntity.ok(characterService.addCharacter(characterRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Character>> deleteCharacterById(@PathVariable("id") String id) {
        return ResponseEntity.ok(characterService.deleteCharacterById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Character> udpateCharacterById(@PathVariable("id") String id, @RequestBody CharacterRequestDto characterRequestDto) {
        return ResponseEntity.ok(characterService.updateCharacterById(id, characterRequestDto));
    }
}
