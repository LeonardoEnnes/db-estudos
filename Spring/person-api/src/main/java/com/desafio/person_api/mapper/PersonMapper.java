package com.desafio.person_api.mapper;

import java.time.LocalDate;
import java.time.Period;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import com.desafio.person_api.domain.entity.Person;
import com.desafio.person_api.dto.PersonResponseDto;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    
    @Mapping(target = "idade", source = "dataNascimento", qualifiedByName = "calculateAge")
    @Mapping(target = "address", source = "addresses")
    PersonResponseDto toDto(Person person);

    @Named("calculateAge")
    default Integer calculateAge(LocalDate birthDate) {
        if (birthDate == null) return null;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
