package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.exception.LivelloNotFoundException;
import it.nextre.academy.nxtlearn.exception.RuoloNotFoundException;
import it.nextre.academy.nxtlearn.model.Livello;
import it.nextre.academy.nxtlearn.model.Ruolo;
import it.nextre.academy.nxtlearn.repository.LivelloRepository;
import it.nextre.academy.nxtlearn.repository.RuoloRepository;
import it.nextre.academy.nxtlearn.service.LivelloService;
import it.nextre.academy.nxtlearn.service.RuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuoloServiceImpl implements RuoloService {

    // i repository restituiscono Optional<T> ma i servizi devono lavorare con T
    @Autowired
    RuoloRepository ruoloRepository;

    @Override
    public Ruolo findById(Integer id) {
        Ruolo r = ruoloRepository.findById(id).orElseThrow(()->new RuoloNotFoundException());
        return r;
    }
    @Override
    public List<Ruolo> getAll() {
        return ruoloRepository.findAll();
    }

    @Override
    public Ruolo save(Ruolo ruolo) {
        return ruoloRepository.save(ruolo);
    }

    @Override
    public Ruolo getByName(String name) {
        return ruoloRepository.findByName(name).orElseThrow(()->new RuoloNotFoundException());
    }
}//end class