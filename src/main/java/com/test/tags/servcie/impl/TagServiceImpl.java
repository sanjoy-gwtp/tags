package com.test.tags.servcie.impl;

import com.test.tags.entities.Tag;
import com.test.tags.repository.TagRepository;
import com.test.tags.servcie.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sanjoy
 * on 5/27/21
 */
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;


    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag saveTag(Tag tag) {
      return tagRepository.save(tag);
    }

    @Override
    public Tag getTagByKey(String key) {
        return tagRepository.findByKey(key);
    }

    @Override
    public void deleteTag(String key) {
        tagRepository.delete(tagRepository.findByKey(key));
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public boolean exist(String key) {
        return tagRepository.findByKey(key)!=null;
    }
}
