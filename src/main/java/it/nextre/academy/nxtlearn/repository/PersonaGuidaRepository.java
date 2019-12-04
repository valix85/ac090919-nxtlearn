package it.nextre.academy.nxtlearn.repository;

import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.model.PersonaGuida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaGuidaRepository extends JpaRepository<PersonaGuida, Integer> {
    boolean existsByGuidaAndPersona(Guida guida, Persona loggedPersona);
}
