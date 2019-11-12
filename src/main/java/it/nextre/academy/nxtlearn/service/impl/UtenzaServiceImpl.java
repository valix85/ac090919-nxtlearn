package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.model.Utenza;
import it.nextre.academy.nxtlearn.repository.UtenzaRepository;
import it.nextre.academy.nxtlearn.service.UtenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenzaServiceImpl implements UtenzaService {

    @Autowired
    UtenzaRepository utenzaRepository;

    @Override
    public Utenza create(Utenza ut) {
        return utenzaRepository.save(ut);
    }
}//end class
