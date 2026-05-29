package com.desafio.person_api.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.desafio.person_api.domain.entity.Person;

public record PersonResponseDto(
    String id,
    String nome,
    LocalDate dataNascimento,
    Integer idade,
    String cpf,
    List<AddressDto> address
    
) {
    public static PersonResponseDto fromEntity(Person person) {
        int calculoIdade = Period.between(person.getDataNascimento(), LocalDate.now()).getYears();

        List<AddressDto> addressDTOs = person.getAddresses().stream()
            .map(a -> new AddressDto(a.getId(), a.getRua(), a.getNumero(), a.getBairro(), a.getCidade(), a.getEstado(), a.getCep(), a.isPrincipal()))
            .toList();

        return new PersonResponseDto(
            person.getId(),
            person.getNome(),
            person.getDataNascimento(),
            calculoIdade,
            person.getCpf(),
            addressDTOs
        );
    }
}
