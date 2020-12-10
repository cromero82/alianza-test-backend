package com.alianza.test;

import com.alianza.test.controller.ClienteController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.nio.file.Files;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ClienteController controller;

    @Before
    public void setUp() throws  Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    };

    @Test
    public void createTest() throws Exception {
        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
        File resource = new ClassPathResource("/static/data/cliente.json").getFile();
        String resourceData = new String(Files.readAllBytes(resource.toPath()));
        ObjectMapper mapper=new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/cliente-api")
                .content(resourceData)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MEDIA_TYPE_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.id", new GreaterThan<Integer>(0)))
        ;
    }

    @Test
    public void healthTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cliente-api/healt"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("ok running!"));
    }
}
