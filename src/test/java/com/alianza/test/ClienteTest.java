package com.alianza.test;

import com.alianza.test.controller.ClienteController;
import com.alianza.test.model.entity.Cliente;
import com.alianza.test.service.impl.ClienteService;
import com.alianza.test.service.interfaz.IClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
public class ClienteTest {
    @Autowired
    private ClienteController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;
//    @MockBean
    @Autowired(required = true)
    private IClienteService service;

    @Test
    public void test() {
        this.restTemplate.getForEntity("/v1/cliente-api/findById",
                String.class,1);
    }

//    @Test
//    public void simpleResult() throws Exception {
//        // given
//        Cliente cliente = new Cliente();
//        cliente.setNombre("Test Nombre");
//        cliente.setSharedKey("testNombre");
//        cliente.setFechaCreacion(new Date());
//        cliente.setCorreo("testcorreo@mail.co");
//
//        doReturn(Optional.of(new Cliente())).when(service).create(cliente);
//        //doReturn(productDTO).when(productMapper).toProductDTO(any(Product.class));
//
//        // when + then
//        this.mockMvc.perform(post("v1/cliente-api"))
//                .andExpect(status().isOk())
//                //.andExpect(jsonPath("$.name", is(productDTO.getName())))
//        ;
//    }

  //  @Test
//    public void simpleResult() throws Exception {
//        Cliente cliente = new Cliente();
//        cliente.setNombre("Test Nombre");
//        cliente.setSharedKey("testNombre");
//        cliente.setFechaCreacion(new Date());
//        cliente.setCorreo("testcorreo@mail.co");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        HttpEntity<Cliente> entity = new HttpEntity<Cliente>(cliente, headers);
//        String dataContent = "{\"id\":0,\"nombre\":\"Nombre-Test\",\"sharedKey\":\"nombreTEst\",\"correo\":\"correoTest@mail.co\",\"fechaCreacion\":\"2020-12-06T05:00:00.000+00:00\"}";
//        ResponseEntity<Cliente> postDoctor = restTemplate.exchange("v1/cliente-api", HttpMethod.POST, entity, Cliente.class);
//        Assert.assertTrue(postDoctor.getStatusCode().equals(HttpStatus.CREATED));
//        Assert.assertNotNull(postDoctor.getBody().getId());
//    }

//    @Test
//    public void contextLoads() throws Exception {
//        assertThat(controller).isNotNull();
//    }

//    @Test
//    public void createTest()   throws Exception {
////        Cliente cliente = new Cliente();
////        cliente.setNombre("Test Nombre");
////        cliente.setSharedKey("testNombre");
////        cliente.setFechaCreacion(new Date());
////        cliente.setCorreo("testcorreo@mail.co");
//
//        mvc.perform( MockMvcRequestBuilders
//                .post("v1/cliente-api")
//                .content("{\"id\":0,\"nombre\":\"Nombre-Test\",\"sharedKey\":\"nombreTEst\",\"correo\":\"correoTest@mail.co\",\"fechaCreacion\":\"2020-12-06T05:00:00.000+00:00\"}")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                //.andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists())
//        ;
//
//        //mvc.perform(get("v1/cliente-api"));
//
////        mvc.perform(get("/api/employees")
////                .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk());
//
////        List<Employee> allEmployees = Arrays.asList(alex);
////
////        given(service.getAllEmployees()).willReturn(allEmployees);
////
////        mvc.perform(get("/api/employees")
////                .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$", hasSize(1)))
////                .andExpect(jsonPath("$[0].name", is(alex.getName())));
//    }

//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
