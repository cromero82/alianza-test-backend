package com.alianza.test;

import com.alianza.test.controller.ClienteController;
import com.alianza.test.service.interfaz.IClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
public class Cliente2 {

    private MockMvc mockMvc;


    @InjectMocks
    private ClienteController controller;

//    @Autowired
//    @MockBean
//    @Autowired(required = true)
    //@Mock
//@Autowired
//    private IClienteService service;

    @Before
    public void setUp() throws  Exception{
        MockitoAnnotations.initMocks(this);
        //controller.service = this.service;
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        //mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeAccountController(employeeService)).build();
    };
    //TypeReference.class.getResourceAsStream("~/data/cliente.json")
    @Test
    public void createTest() throws Exception {
        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));

        File resource = new ClassPathResource("/static/data/cliente.json").getFile();
        String resourceData = new String(Files.readAllBytes(resource.toPath()));
        resourceData = "{\"id\":0,\"sharedKey\":\"catalinaAristizabalTest\",\"fechaCreacion\":\"2020-12-06T05:00:00.000+00:00\",\"nombre\":\"CATALINA ARISTIZABAL TEST\",\"telefono\":\"3119996666\",\"correo\":\"catalinzaatest@mail.co\"}";
        //InputStream inputStream = TypeReference.class.getResourceAsStream("/data/cliente.json");

        ObjectMapper mapper=new ObjectMapper();

        //String jsonString=mapperwriteValueAsString(mapper.readValue(new File("path/to/file",Object.class));
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/cliente-api")
                .content(resourceData)
                //.content("{\"id\":0,\"nombre\":\"Nombre-Test\",\"sharedKey\":\"nombreTEst\",\"correo\":\"correoTest@mail.co\",\"fechaCreacion\":\"2020-12-06T05:00:00.000+00:00\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MEDIA_TYPE_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.id", new GreaterThan<Integer>(0)))
                //.andExpect(jsonPath("$.organization.id", new GreaterThan<Integer>(0)))
        ;
    }

    @Test
    public void testHelloWOrld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cliente-api/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello World!"));
    }

}
