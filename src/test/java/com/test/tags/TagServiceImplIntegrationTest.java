package com.test.tags;


import com.test.tags.entities.Tag;
import com.test.tags.repository.TagRepository;
import com.test.tags.servcie.TagService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TagServiceImplIntegrationTest {

    @Autowired
    private TagService tagService;

    @MockBean
    private TagRepository tagRepository;


    @BeforeEach
    void setUp() {
        Tag tag1 = new Tag("a","1");
        Tag tag2 = new Tag("b","2");
        Tag tag3 = new Tag("c","3");

        List<Tag> allTags = Arrays.asList(tag1, tag2, tag3);

        Mockito.when(tagRepository.findByKey(tag1.getKey())).thenReturn(tag1);
        Mockito.when(tagRepository.findByKey(tag2.getKey())).thenReturn(tag2);
        Mockito.when(tagRepository.findByKey("wrong_name")).thenReturn(null);
        Mockito.when(tagRepository.findAll()).thenReturn(allTags);
    }

    @Test
    public void whenValidKey_thenTagShouldBeFound() {
        String key = "a";
        Tag found = tagService.getTagByKey(key);
        assertThat(found.getKey()).isEqualTo(key);
    }

    @Test
    public void whenInValidName_thenTagShouldNotBeFound() {
        Tag fromDb = tagService.getTagByKey("wrong_name");
        assertThat(fromDb).isNull();
        verifyFindByNameIsCalledOnce("wrong_name");
    }

    @Test
    public void given3Tags_whengetAll_thenReturn3Records() {
        Tag tag1 = new Tag("a","1");
        Tag tag2 = new Tag("b","2");
        Tag tag3 = new Tag("c","3");

        List<Tag> allTags = tagService.getAll();
        verifyFindAllTagsIsCalledOnce();
        assertThat(allTags).hasSize(3).extracting(Tag::getValue).contains(tag1.getValue(), tag2.getValue(), tag3.getValue());
    }

    private void verifyFindByNameIsCalledOnce(String key) {
        Mockito.verify(tagRepository, VerificationModeFactory.times(1)).findByKey(key);
        Mockito.reset(tagRepository);
    }

    private void verifyFindAllTagsIsCalledOnce() {
        Mockito.verify(tagRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(tagRepository);
    }
}
