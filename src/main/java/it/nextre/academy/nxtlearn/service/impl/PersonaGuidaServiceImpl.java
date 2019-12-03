package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.model.PersonaGuida;
import it.nextre.academy.nxtlearn.model.key.PersonaGuidaId;
import it.nextre.academy.nxtlearn.repository.GuidaRepository;
import it.nextre.academy.nxtlearn.repository.PersonaGuidaRepository;
import it.nextre.academy.nxtlearn.repository.PersonaRepository;
import it.nextre.academy.nxtlearn.repository.UtenzaRepository;
import it.nextre.academy.nxtlearn.service.PersonaGuidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaGuidaServiceImpl implements PersonaGuidaService {
    @Autowired
    PersonaGuidaRepository personaGuidaRepository;
    @Autowired
    UtenzaRepository utenzaRepository;
    @Autowired
    GuidaRepository guidaRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public PersonaGuida addRelation(Integer utenzaId, Integer guidaId) {
        //Persona p = utenzaRepository.findByEmail(email).getPersona();
        Persona p = utenzaRepository.findById(utenzaId).get().getPersona();
        Guida g = guidaRepository.getOne(guidaId);
        PersonaGuidaId tmp = new PersonaGuidaId();
        tmp.setGuidaId(guidaId);
        tmp.setPersonaId(p.getId());
        PersonaGuida pg = new PersonaGuida();
        pg.setId(tmp);
        pg.setPersona(p);
        pg.setGuida(g);
        personaGuidaRepository.save(pg);
        return pg;
    }
}//end class
