package it.nextre.academy.nxtlearn.repository;

import it.nextre.academy.nxtlearn.model.Guida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuidaRepository extends JpaRepository<Guida, Integer> {
}
