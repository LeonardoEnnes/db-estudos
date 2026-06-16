package com.desafio.person_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.desafio.person_api.domain.entity.Person; 

@Repository
public interface PersonRepository extends JpaRepository<Person, String>{
    boolean existsByCpf(String cpf);
    boolean existsByCpfAndIdNot(String cpf, String id);
}
