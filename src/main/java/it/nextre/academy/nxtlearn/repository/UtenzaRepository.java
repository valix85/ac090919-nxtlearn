package it.nextre.academy.nxtlearn.repository;

import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.model.Utenza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenzaRepository extends JpaRepository<Utenza, Integer> {

    Utenza findByEmail(String email);

}//end class
