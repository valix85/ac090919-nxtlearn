package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.model.Livello;
import it.nextre.academy.nxtlearn.repository.LivelloRepository;
import it.nextre.academy.nxtlearn.service.LivelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivelloServiceImpl implements LivelloService {
    @Autowired
    LivelloRepository livelloRepository;
    @Override
    public Optional<Livello> findById(Integer id) {
        Optional<Livello> l = Optional.of(livelloRepository.findById(id).orElse(null));
        return l;
    }
    @Override
    public List<Livello> getAll() {
        return livelloRepository.findAll();
    }
}//end class