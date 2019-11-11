package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.exception.LivelloNotFoundException;
import it.nextre.academy.nxtlearn.model.Livello;
import it.nextre.academy.nxtlearn.repository.LivelloRepository;
import it.nextre.academy.nxtlearn.service.LivelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivelloServiceImpl implements LivelloService {

    // i repository restituiscono Optional<T> ma i servizi devono lavorare con T
    @Autowired
    LivelloRepository livelloRepository;

    @Override
    public Livello findById(Integer id) {
        Livello l = livelloRepository.findById(id).orElseThrow(()->new LivelloNotFoundException());
        return l;
    }
    @Override
    public List<Livello> getAll() {
        return livelloRepository.findAll();
    }
}//end class