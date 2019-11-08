package it.nextre.academy.nxtlearn.repository;

import it.nextre.academy.nxtlearn.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findAllByCognome(String cognome);

}//end class
