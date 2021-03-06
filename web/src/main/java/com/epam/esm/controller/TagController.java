package com.epam.esm.controller;

import com.epam.esm.dao.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> findAll() {
        return tagService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Tag findById(@PathVariable int id) {
        return tagService.findById(id);
    }

    @PostMapping
    public List<Tag> save(@RequestBody Tag tag) {
        tagService.save(tag);
        return tagService.findAll();
    }

    @DeleteMapping("/{id}")
    public List<Tag> delete(@PathVariable int id) {
        tagService.delete(id);
        return tagService.findAll();
    }
}
