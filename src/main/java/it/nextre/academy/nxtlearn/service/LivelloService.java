package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.model.Livello;

import java.util.List;
import java.util.Optional;

public interface LivelloService {

    Optional<Livello> findById(Integer id);

    List<Livello> getAll();
}
