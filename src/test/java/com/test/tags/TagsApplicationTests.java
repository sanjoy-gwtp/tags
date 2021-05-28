package com.test.tags;

import com.test.tags.entities.Tag;
import com.test.tags.repository.TagRepository;
import com.test.tags.servcie.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TagsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TagService tagService;

	@MockBean
	private TagRepository tagRepository;


	@BeforeEach
	void setMockOutput() {

	}


	@DisplayName("save tag")
	@Test
	void testTagSave() {
		Tag tag=new Tag("a","1");
		when(tagRepository.save(tag)).thenReturn(tag);
		assertEquals("1",tagService.saveTag(tag).getValue());
	}

	@DisplayName("get tag by key")
	@Test
	void testTagByKey() {
		when(tagRepository.findByKey("b")).thenReturn(new Tag("b","2"));
		assertEquals("2",tagService.getTagByKey("b").getValue());
	}


	@DisplayName("get All tag")
	@Test
	void testAllTagByKey() {
		when(tagRepository.findAll()).thenReturn(Arrays.asList(new Tag("a","1"),new Tag("b","2"),new Tag("c","3")));
		assertEquals(3,tagService.getAll().size());
	}

}
