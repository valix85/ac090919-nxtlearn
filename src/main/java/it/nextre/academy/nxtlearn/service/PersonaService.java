package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.dto.PersonaDto;
import it.nextre.academy.nxtlearn.model.Persona;

import java.util.List;

public interface PersonaService {

    Persona getRandom();
    Persona getPersona(Integer id);
    List<Persona> getRandoms(Integer numero);
    List<Persona> getPersone();
    Persona newPersona(Persona p);
    boolean deletePersonaById(Integer id);
    Persona update(Persona p);

    PersonaDto toDto(Persona p);
}
