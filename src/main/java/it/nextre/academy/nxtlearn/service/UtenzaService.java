package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.dto.UtenzaDto;
import it.nextre.academy.nxtlearn.model.Utenza;

import java.util.List;

//todo capire se va tenuto
public interface UtenzaService {
    Utenza create(Utenza ut);
    Utenza findByEmail(String email);

    List<Utenza> getUtenze();
    Utenza getUtenza(Integer id);
    UtenzaDto toDto(Utenza u);
    Utenza updateUtenza(Utenza u);
}
