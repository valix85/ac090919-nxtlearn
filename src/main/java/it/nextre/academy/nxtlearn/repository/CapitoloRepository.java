package it.nextre.academy.nxtlearn.repository;

import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Guida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapitoloRepository extends JpaRepository<Capitolo, Integer> {
    List<Capitolo> findAllByGuidaOrderByOrdineCapitolo(Guida guida);
}
