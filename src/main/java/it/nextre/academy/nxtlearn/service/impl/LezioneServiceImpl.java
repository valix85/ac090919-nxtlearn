package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.dto.LezioneDto;
import it.nextre.academy.nxtlearn.model.Lezione;
import it.nextre.academy.nxtlearn.repository.AllegatoRepository;
import it.nextre.academy.nxtlearn.repository.LezioneRepository;
import it.nextre.academy.nxtlearn.service.LezioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LezioneServiceImpl implements LezioneService {

    @Autowired
    LezioneRepository lezioneRepository;

    public Lezione findById(Integer id) {
        Lezione lezione = lezioneRepository.findById(id).orElse(null);
        return lezione;
    }

    @Autowired
    AllegatoRepository allegatoRepository;
    public LezioneDto toDto(Lezione lezione){
        LezioneDto tmp = new LezioneDto(lezione, allegatoRepository);
        return tmp;
    }

}//end class
