package com.desafio.person_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.desafio.person_api.dto.PersonRequestDto;
import com.desafio.person_api.dto.PersonResponseDto;
import com.desafio.person_api.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Sort;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;
    
    @GetMapping
    @Operation(summary = "Listar pessoas", description = "Retorna uma página de pessoas com seus respectivos endereços.")
    public ResponseEntity<Page<PersonResponseDto>> getAll(
            @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable page) {
        return ResponseEntity.ok(personService.findAll(page));
    }
    
    @PostMapping
    @Operation(summary = "Criar pessoa", description = "Cadastra uma nova pessoa e seus endereços. O CPF deve ser único e válido.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos (ex: CPF mal formatado)"),
        @ApiResponse(responseCode = "422", description = "Erro de negócio (ex: CPF já cadastrado ou múltiplos endereços principais)")
    })
    public ResponseEntity<PersonResponseDto> create(@RequestBody @Valid PersonRequestDto dto) { // expl
        PersonResponseDto response = personService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(response);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pessoa", description = "Atualiza os dados de uma pessoa e substitui sua lista de endereços pelo novo conteúdo enviado.")
    public ResponseEntity<PersonResponseDto> update (@PathVariable String id, @RequestBody @Valid PersonRequestDto dto) {
        return ResponseEntity.ok(personService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir pessoa", description = "Remove uma pessoa e todos os seus endereços vinculados permanentemente.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Pessoa excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<Void> delete (@PathVariable String id) {
        personService.delete(id);
        return ResponseEntity.noContent().build(); //
    }

}
