package it.nextre.academy.nxtlearn.repository;

import it.nextre.academy.nxtlearn.model.Allegato;
import it.nextre.academy.nxtlearn.model.Lezione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllegatoRepository extends JpaRepository<Allegato, Integer> {
    List<Allegato> findAllByLezioneOrderByOrdineAllegato(Lezione lezione);
}
