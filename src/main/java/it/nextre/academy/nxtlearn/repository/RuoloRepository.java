package it.nextre.academy.nxtlearn.repository;

import it.nextre.academy.nxtlearn.model.Livello;
import it.nextre.academy.nxtlearn.model.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, Integer> {

    Optional<Ruolo> findByName(String name);

}
