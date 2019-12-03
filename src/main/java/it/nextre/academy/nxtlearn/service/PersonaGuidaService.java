package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.model.PersonaGuida;

public interface PersonaGuidaService {
    PersonaGuida addRelation(Integer utenzaId, Integer guidaId);
}
