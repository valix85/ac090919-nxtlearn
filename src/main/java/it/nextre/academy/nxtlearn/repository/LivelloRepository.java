package it.nextre.academy.nxtlearn.repository;

import it.nextre.academy.nxtlearn.model.Livello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivelloRepository extends JpaRepository<Livello, Integer> {

}
