package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.model.Livello;

import java.util.List;
import java.util.Optional;

public interface LivelloService {
    // i repository restituiscono Optional<T> ma i servizi devono lavorare con T
    Livello findById(Integer id);

    List<Livello> getAll();
}
