package com.desafio.person_api.controller;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import com.desafio.person_api.dto.AddressDto;
import com.desafio.person_api.dto.PersonRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerITest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // isso foi feito pra lidar com datas, tava dando muito bug

    @Test
    @DisplayName("Deve criar uma pessoa com sucesso e retornar 201")
    void shouldCreatePersonSuccessfully() throws Exception {
        var addr = new AddressDto(null, "Rua", "10", "Centro", "Poa", "RS", "90000-000", true);
        var request = new PersonRequestDto("Leonardo", LocalDate.of(2000, 1, 1), "12345678909", List.of(addr));

        mockMvc.perform(post("/api/v1/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Leonardo"))
                .andExpect(jsonPath("$.cpf").value("12345678909"));
    }

    @Test
    @DisplayName("Deve retornar 404 ao tentar deletar ID inexistente")
    void shouldReturn404WhenDeletingInvalidId() throws Exception {
        mockMvc.perform(delete("/api/v1/people/id-inexistente"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Recurso não encontrado"));
    }

    @Test
    @DisplayName("Deve retornar 400 quando enviar CPF inválido")
    void shouldReturn400WhenCpfIsInvalid() throws Exception {
        var request = new PersonRequestDto("Leo", LocalDate.now(), "cpf-errado", List.of());

        mockMvc.perform(post("/api/v1/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Erro de Validação"))
                .andExpect(jsonPath("$.fieldErrors.cpf").exists());
    }

    @Test
    @DisplayName("Deve retornar 422 quando houver mais de um endereço principal")
    void shouldReturn422WhenMultipleMainAddresses() throws Exception {
        var addr1 = new AddressDto(null, "Rua A", "1", "B", "C", "D", "12345-678", true);
        var addr2 = new AddressDto(null, "Rua B", "2", "B", "C", "D", "12345-678", true);
        var request = new PersonRequestDto("Leonardo", LocalDate.now(), "98765432100", List.of(addr1, addr2));

        mockMvc.perform(post("/api/v1/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is(422))
                .andExpect(jsonPath("$.message").value("Uma pessoa não pode ter mais de um endereço principal."));
    }

    @Test
    @DisplayName("Deve listar pessoas paginadas")
    void shouldListPeoplePaged() throws Exception {
        mockMvc.perform(get("/api/v1/people")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    @DisplayName("Deve permitir cadastrar uma pessoa com múltiplos endereços (apenas um principal)")
    void shouldAllowMultipleAddressesWithOneMain() throws Exception {
        var addr1 = new AddressDto(null, "Rua Principal", "1", "Centro", "Poa", "RS", "90000-000", true);
        var addr2 = new AddressDto(null, "Rua Secundária", "2", "Bairro", "Poa", "RS", "90000-111", false);
        
        // var request = new PersonRequestDto(leotteste"", LocalDate.now(), "13264987002", List.of(addr1, addr2));
        var request = new PersonRequestDto("Leonardo", LocalDate.now(), "65432198746", List.of(addr1, addr2));

        mockMvc.perform(post("/api/v1/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Leonardo"))
                .andExpect(jsonPath("$.address").isArray())
                .andExpect(jsonPath("$.address.length()").value(2)) // Valida que o banco salvou os dois
                .andExpect(jsonPath("$.address[0].principal").value(true))
                .andExpect(jsonPath("$.address[1].principal").value(false));
    }
}
