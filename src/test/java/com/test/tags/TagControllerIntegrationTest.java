package com.test.tags;


import com.test.tags.controller.TagController;
import com.test.tags.entities.Tag;
import com.test.tags.servcie.TagService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TagService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostTag_thenCreateTag() throws Exception {
        Tag tag = new Tag("a","1");
        given(service.saveTag(Mockito.any())).willReturn(tag);

        mvc.perform(post("/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(tag)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.value", is("1")));
        verify(service, VerificationModeFactory.times(1)).saveTag(Mockito.any());
        reset(service);
    }

    @Test
    public void givenTags_whenGetTags_thenReturnJsonArray() throws Exception {
        Tag tag1 = new Tag("a","1");
        Tag tag2 = new Tag("b","2");
        Tag tag3 = new Tag("c","3");

        List<Tag> allTags = Arrays.asList(tag1, tag2, tag3);

        given(service.getAll()).willReturn(allTags);

        mvc.perform(get("/tags").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].value", is(tag1.getValue())))
                .andExpect(jsonPath("$[1].value", is(tag2.getValue())))
                .andExpect(jsonPath("$[2].value", is(tag3.getValue())));
        verify(service, VerificationModeFactory.times(1)).getAll();
        reset(service);
    }

}