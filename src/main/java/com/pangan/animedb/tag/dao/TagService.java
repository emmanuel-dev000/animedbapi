package com.pangan.animedb.tag.dao;

import com.pangan.animedb.tag.dto.TagRequestDto;
import com.pangan.animedb.tag.exception.IncompleteTagFieldsException;
import com.pangan.animedb.tag.exception.TagNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTags() throws TagNotFoundException {
        if (tagRepository.findAll().isEmpty()) {
             throw new TagNotFoundException();
        }

        return tagRepository.findAll();
    }

    public Tag getTagById(String id) throws TagNotFoundException {
        if (!tagRepository.existsById(id) || tagRepository.findById(id).isEmpty()) {
            throw new TagNotFoundException();
        }

        return tagRepository.findById(id).get();
    }

    public Tag addTag(TagRequestDto tagRequestDto) throws IncompleteTagFieldsException {
        if (!StringUtils.hasText(tagRequestDto.name())) {
            throw new IncompleteTagFieldsException();
        }

        Tag tag = new Tag();
        tag.setName(tagRequestDto.name());
        return tagRepository.save(tag);
    }

    public String deleteTagById(String id) throws TagNotFoundException {
        if (!tagRepository.existsById(id) || tagRepository.findById(id).isEmpty()) {
            throw new TagNotFoundException();
        }

        tagRepository.deleteById(id);
        return "Tag is successfully delete from the database";
    }

    public Tag updateTagById(String id, TagRequestDto tagRequestDto) throws TagNotFoundException, IncompleteTagFieldsException {
        if (!StringUtils.hasText(tagRequestDto.name())) {
            throw new IncompleteTagFieldsException();
        }

        if (!tagRepository.existsById(id) || tagRepository.findById(id).isEmpty()) {
            throw new TagNotFoundException();
        }

        Tag tag = tagRepository.findById(id).get();
        tag.setName(tagRequestDto.name());
        return tagRepository.save(tag);
    }
}
