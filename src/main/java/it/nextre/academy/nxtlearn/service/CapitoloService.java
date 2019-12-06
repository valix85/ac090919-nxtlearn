package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.dto.CapitoloDto;
import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Guida;

import java.util.List;

public interface CapitoloService {
    Capitolo findById(Integer id);

    List<Capitolo> getAll();

    Capitolo newCapitolo(Capitolo c);

    Capitolo update(Capitolo c);

    Boolean deleteById(Integer id);

    List<Capitolo> findCapitoliByGuida(Guida byId);

    Capitolo save(Capitolo capitolo);

    CapitoloDto toDto(Capitolo capitolo);
}