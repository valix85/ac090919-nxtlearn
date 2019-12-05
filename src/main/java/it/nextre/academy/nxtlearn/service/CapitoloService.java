package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.model.Capitolo;

import java.util.List;

public interface CapitoloService {
    Capitolo findById(Integer id);

    List<Capitolo> getAll();

    Capitolo newCapitolo(Capitolo c);

    Capitolo update(Capitolo c);

    Boolean deleteById(Integer id);
}