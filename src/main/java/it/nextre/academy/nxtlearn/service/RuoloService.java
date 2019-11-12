package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.model.Livello;
import it.nextre.academy.nxtlearn.model.Ruolo;

import java.util.List;

public interface RuoloService {
    // i repository restituiscono Optional<T> ma i servizi devono lavorare con T
    Ruolo findById(Integer id);
    List<Ruolo> getAll();
    Ruolo save(Ruolo ruolo);
    Ruolo getByName(String name);
}
