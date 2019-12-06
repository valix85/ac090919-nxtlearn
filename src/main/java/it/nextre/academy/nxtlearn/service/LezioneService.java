package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.dto.LezioneDto;
import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Lezione;

import java.util.List;

public interface LezioneService {

    Lezione findById(Integer id);
    LezioneDto toDto(Lezione lezione);

    List<Lezione> findLezioneByGuidaCapitolo(Capitolo capitolo);

    Boolean deleteById(Integer id);

    Lezione save(Lezione lezione);

    Lezione newLezione(Lezione lezione);

    Lezione update(Lezione lezione);

    // List<Lezione> getAll();
}//end class
