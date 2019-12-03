package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.dto.LezioneDto;
import it.nextre.academy.nxtlearn.model.Lezione;

public interface LezioneService {

    Lezione findById(Integer id);
    LezioneDto toDto(Lezione lezione);
}//end class
