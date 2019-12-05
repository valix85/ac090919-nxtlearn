package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.model.PersonaGuida;

import java.util.Arrays;
import java.util.List;

public interface PersonaGuidaService {
    PersonaGuida addRelation(Integer utenzaId, Integer guidaId);

    boolean checkRelation(Integer idGuida);

    List<Guida> getGuideByUser(Persona persona);
    Boolean deleteGuidesToUser(Persona persona);
    PersonaGuida newRelation(Persona persona, Guida guida);
}
