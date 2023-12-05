package com.fr.exalt.userinterface.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.exalt.core.domain.Client;
import com.fr.exalt.infra.repository.ClientJpaRepository;
import com.fr.exalt.userinterface.config.AbstractIntegrationTest;
import com.fr.exalt.userinterface.data.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerIT extends AbstractIntegrationTest {

    private static final String URL_ClIENT = "/api/client";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientJpaRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUpAll() {
        repository.deleteAll();
        repository.saveAll(Data.getClients());
    }

    @Test
    void return_list_client_when_get_all_clients() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL_ClIENT)
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void return_client_when_save_client() throws Exception {
        var client = Client.builder()
                .firstName("user_firstName")
                .lastName("user_LastName")
                .build();
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL_ClIENT)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(client))
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("user_firstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("user_LastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void return_void_when_delete_client_by_id() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URL_ClIENT + "/1")
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void return_client_when_update_client() throws Exception {
        var client = Client.builder()
                .id(1L)
                .firstName("user_ervan")
                .lastName("user_salin")
                .build();
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URL_ClIENT)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(client))
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("user_ervan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("user_salin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void throw_failed_update_exception_when_update_client() throws Exception {
        var client = Client.builder()
                .firstName("user_ervan")
                .lastName("user_salin")
                .build();
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URL_ClIENT)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(client))
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.title", is("Bad Request")))
                .andExpect(jsonPath("$.detail", is("Failed Update: client null not exist.")))
                .andExpect(jsonPath("$.status", is(400)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void return_client_when_find_client_by_id() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL_ClIENT + "/2")
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("jean-paul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("gaultier"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void throw_not_found_exception_when_update_client() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL_ClIENT + "/999")
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder).andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.title", is("Not Found")))
                .andExpect(jsonPath("$.detail", is("Not found: client 999 not exist.")))
                .andExpect(jsonPath("$.status", is(404)))
                .andDo(MockMvcResultHandlers.print());
    }
}