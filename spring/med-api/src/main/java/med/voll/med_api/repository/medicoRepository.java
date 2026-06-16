package med.voll.med_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import med.voll.med_api.domains.medico;

@Repository
public interface medicoRepository extends JpaRepository<medico, Long> {

    
}