package com.test.tags;


import com.test.tags.entities.Tag;
import com.test.tags.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TagRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void whenFindByKey_thenReturnEmployee() {
        Tag tag = new Tag("a","1");
        entityManager.persistAndFlush(tag);

        Tag found = tagRepository.findByKey(tag.getKey());
        assertThat(found.getValue()).isEqualTo(tag.getValue());
    }

    @Test
    public void whenInvalidKey_thenReturnNull() {
        Tag fromDb = tagRepository.findByKey("doesNotExist");
        assertThat(fromDb).isNull();
    }


    @Test
    public void givenSetOfTags_whenFindAll_thenReturnAllTags() {
        Tag tag1 = new Tag("a","1");
        Tag tag2 = new Tag("b","2");
        Tag tag3 = new Tag("c","3");


        entityManager.persist(tag1);
        entityManager.persist(tag2);
        entityManager.persist(tag3);
        entityManager.flush();

        List<Tag> allEmployees = tagRepository.findAll();

        assertThat(allEmployees).hasSize(3).extracting(Tag::getValue)
                .containsOnly(tag1.getValue(), tag2.getValue(), tag3.getValue());
    }
}
