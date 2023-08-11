package com.pangan.animedb.tag.dao;

import com.pangan.animedb.tag.dto.TagRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") String id) {
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    @PostMapping
    public ResponseEntity<Tag> addTag(@RequestBody TagRequestDto tagRequestDto) {
        return ResponseEntity.ok(tagService.addTag(tagRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Tag>> deleteTagById(@PathVariable("id") String id) {
        return ResponseEntity.ok(tagService.deleteTagById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tag> updateTagById(@PathVariable("id") String id, @RequestBody TagRequestDto tagRequestDto) {
        return ResponseEntity.ok(tagService.updateTagById(id, tagRequestDto));
    }
}
