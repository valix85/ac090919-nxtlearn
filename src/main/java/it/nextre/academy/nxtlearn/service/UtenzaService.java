package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.model.Utenza;

//todo capire se va tenuto
public interface UtenzaService {
    Utenza create(Utenza ut);

    Utenza findByEmail(String email);
}
