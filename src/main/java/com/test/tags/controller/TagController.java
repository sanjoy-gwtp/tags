package com.test.tags.controller;


import com.test.tags.entities.Tag;
import com.test.tags.exception.TagNotFoundException;
import com.test.tags.servcie.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * @author sanjoy
 * on 5/27/21
 */

@RestController
@RequestMapping(path = "/tags",produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTag(@RequestBody Tag tag) {
        return new ResponseEntity<>(tagService.saveTag(tag), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllTag() {
        List<Tag> tagList = tagService.getAll();
        return ResponseEntity.ok(tagList);
    }


    @GetMapping(path = "/{key}")
    public ResponseEntity<?> getTagByKey(@PathVariable("key") String key) {

      Tag tag=  tagService.getTagByKey(key);
        return ResponseEntity.ok(tag);
    }

    @DeleteMapping
    public ResponseEntity deleteTag(@RequestParam(name="key",required = true)String key) {
        if(tagService.exist(key)) {
            tagService.deleteTag(key);
            return ResponseEntity.accepted().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
