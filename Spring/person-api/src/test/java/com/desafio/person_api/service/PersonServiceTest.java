package com.desafio.person_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.desafio.person_api.domain.entity.Address;
import com.desafio.person_api.domain.entity.Person;
import com.desafio.person_api.dto.AddressDto;
import com.desafio.person_api.dto.PersonRequestDto;
import com.desafio.person_api.dto.PersonResponseDto;
import com.desafio.person_api.exception.BusinessException;
import com.desafio.person_api.mapper.PersonMapper;
import com.desafio.person_api.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Mock 
    private PersonMapper personMapper;

    @Test
    @DisplayName("Deve lançar erro ao tentar cadastrar cpf existente")
    void shouldThrowErrorWhenCPFAlreadyExists() {
        var request = new PersonRequestDto("leo", LocalDate.now(), "00000000000", List.of());
        when(personRepository.existsByCpf(anyString()))
            .thenReturn(true);

        assertThrows(BusinessException.class, () -> personService.create(request));
        verify(personRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar cadastrar mais de um endereço principal")
    void shouldThrowErrorWhenMultipleMainAddresses() {
        var addr1 = new AddressDto(null, "Rua A", "1", "B", "C", "D", "12345-678", true);
        var addr2 = new AddressDto(null, "Rua B", "2", "B", "C", "D", "12345-678", true);
        var request = new PersonRequestDto("Leo", LocalDate.now(), "12345678901", List.of(addr1, addr2));

        BusinessException ex = assertThrows(BusinessException.class, () -> personService.validarEAdicionarEnderecos(new Person(), request));
        assertEquals("Uma pessoa não pode ter mais de um endereço principal.", ex.getMessage());
    }

    @Test
    @DisplayName("Deve ser possível adicionar múltiplos endereços")
    void shouldBePossibleAddMultipleAddresses() {
        var addr1 = new AddressDto(null, "Rua A", "100", "Bairro A", "Cidade", "RS", "90000-000", true);
        var addr2 = new AddressDto(null, "Rua B", "200", "Bairro B", "Cidade", "RS", "90000-000", false); // n principal
        
        var request = new PersonRequestDto("Leonardo", LocalDate.of(2000, 1, 1), "00000002011", List.of(addr1, addr2));
        
        when(personRepository.existsByCpf(anyString())).thenReturn(false);
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(personMapper.toDto(any(Person.class))).thenReturn(new PersonResponseDto(null, "Leonardo", null, 25, "00000002011", List.of()));

        PersonResponseDto response = personService.create(request);

        assertNotNull(response);
        verify(personRepository).save(argThat(person -> 
            person.getAddresses().size() == 2 && // Garante que os 2 endereços foram adicionados
            person.getAddresses().stream().filter(Address::isPrincipal).count() == 1 // Garante que só 1 é principal
        ));
    }
    
}
