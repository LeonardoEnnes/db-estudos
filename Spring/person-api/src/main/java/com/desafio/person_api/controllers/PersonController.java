package com.desafio.person_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.desafio.person_api.dto.PersonRequestDto;
import com.desafio.person_api.dto.PersonResponseDto;
import com.desafio.person_api.service.PersonService;
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
    public ResponseEntity<Page<PersonResponseDto>> getAll(
            @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable page) {
        return ResponseEntity.ok(personService.findAll(page));
    }
    
    @PostMapping
    public ResponseEntity<PersonResponseDto> create(@RequestBody @Valid PersonRequestDto dto) { // expl
        PersonResponseDto response = personService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDto> update (@PathVariable String id, @RequestBody @Valid PersonRequestDto dto) {
        return ResponseEntity.ok(personService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable String id) {
        personService.delete(id);
        return ResponseEntity.noContent().build(); //
    }

}
