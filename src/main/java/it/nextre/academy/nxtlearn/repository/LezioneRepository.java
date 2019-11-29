package it.nextre.academy.nxtlearn.repository;

import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.model.Lezione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LezioneRepository extends JpaRepository<Lezione, Integer> {
    List<Lezione> findAllByCapitoloOrderByOrdineLezione(Capitolo capitolo);
}
