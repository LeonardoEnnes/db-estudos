package com.desafio.person_api.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.person_api.domain.entity.Address;
import com.desafio.person_api.domain.entity.Person;
import com.desafio.person_api.dto.AddressDto;
import com.desafio.person_api.dto.PersonRequestDto;
import com.desafio.person_api.dto.PersonResponseDto;
import com.desafio.person_api.exception.BusinessException;
import com.desafio.person_api.mapper.PersonMapper;
import com.desafio.person_api.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {
    
    private final PersonRepository personRepository; 
    private final PersonMapper personMapper;

    @Transactional(readOnly = true) // ?
    public Page<PersonResponseDto> findAll(Pageable pageable) {
        return personRepository.findAll(pageable)
            .map(personMapper::toDto); 
    }

    @Transactional
    public PersonResponseDto create(PersonRequestDto dto) {
        if (personRepository.existsByCpf(dto.cpf())) {
            throw new BusinessException("CPF cadastrado no sistema");
        }

        Person person = new Person();
        person.setNome(dto.nome());
        person.setDataNascimento(dto.dataNascimento());
        person.setCpf(dto.cpf());

        if (dto.addresses() != null) {
            validarEAdicionarEnderecos(person, dto);
        }

        Person savedPerson = personRepository.save(person);
        return personMapper.toDto(savedPerson);
    }

    @Transactional
    public PersonResponseDto update (String id, PersonRequestDto dto) {
        Person person = personRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        if (personRepository.existsByCpfAndIdNot(dto.cpf(), id)) {
            throw new BusinessException("cpf ja utilizado por outro cadastro"); 
        }

        person.setNome(dto.nome());
        person.setDataNascimento(dto.dataNascimento());
        person.setCpf(dto.cpf());

        person.getAddresses().clear(); // 

        if (dto.addresses() != null) {
            validarEAdicionarEnderecos(person, dto);
        }

        Person updatePerson = personRepository.save(person);
        return personMapper.toDto(updatePerson);
    }

    @Transactional
    public void delete (String id) {
        if(!personRepository.existsById(id)){
            throw new EntityNotFoundException("Pessoa não encontrada");
        }

        personRepository.deleteById(id);
    }

    public void validarEAdicionarEnderecos(Person person, PersonRequestDto dto) {
        long principais = dto.addresses().stream().filter(AddressDto::principal).count();
        if (principais > 1) {
            throw new BusinessException("Uma pessoa não pode ter mais de um endereço principal.");
        }

        dto.addresses().forEach(a -> {
            Address address = new Address();
            address.setRua(a.rua());
            address.setNumero(a.numero());
            address.setBairro(a.bairro());
            address.setCidade(a.cidade());
            address.setEstado(a.estado());
            address.setCep(a.cep());
            address.setPrincipal(a.principal());
            person.addAddress(address);
        });
    }
}
