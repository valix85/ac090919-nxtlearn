package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.config.CustomUserDetails;
import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.model.PersonaGuida;
import it.nextre.academy.nxtlearn.model.key.PersonaGuidaId;
import it.nextre.academy.nxtlearn.myutils.MySupport;
import it.nextre.academy.nxtlearn.repository.GuidaRepository;
import it.nextre.academy.nxtlearn.repository.PersonaGuidaRepository;
import it.nextre.academy.nxtlearn.repository.PersonaRepository;
import it.nextre.academy.nxtlearn.repository.UtenzaRepository;
import it.nextre.academy.nxtlearn.service.PersonaGuidaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaGuidaServiceImpl implements PersonaGuidaService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Override
    public boolean checkRelation(Integer guidaId) {
        UserDetails loggedUser = MySupport.getLoggedUser();
        if (loggedUser!=null && loggedUser instanceof CustomUserDetails && ((CustomUserDetails)loggedUser).getId()!=null) {
            Integer loggedId = ((CustomUserDetails) loggedUser).getId();
            Persona loggedPersona = null;
            try {
                loggedPersona = utenzaRepository.findById(loggedId).get().getPersona();
            }catch (NullPointerException ex){
                logger.error("UTENZA SENZA PROFILO PERSONA. UTENZA ID: "+loggedId);
                return false;
            }
            Guida g = guidaRepository.getOne(guidaId);
            return personaGuidaRepository.existsByGuidaAndPersona(g, loggedPersona);
        }else {
            return false;
        }
    }



    @Override
    public List<Guida> getGuideByUser(Persona p) {
        if(p == null || p.getId()==null)
            return new ArrayList<>();
        List<Guida> guideDellUtente = new ArrayList<>();
        /*
        personaGuidaRepository.findAll().stream()
                .filter(relation -> relation.getPersona().getId().equals(p.getId()) )
                .forEach( rel -> guideDellUtente.add(rel.getGuida()));
        */

        guideDellUtente = personaGuidaRepository.findAllByPersonaOrderByDataInizioDesc(p)
                .stream()
                .map(pg -> pg.getGuida())
                .collect(Collectors.toList());

        // guideDellUtente.stream().forEach(System.out::println);
        return guideDellUtente;
    }
    @Override
    public PersonaGuida newRelation(Persona p, Guida g) {
        if (g != null && g.getId()!=null && p!=null && p.getId()!=null) {
            return addRelation(p.getUtenza().getId(),g.getId());
            // return personaGuidaRepository.save(new PersonaGuida(p,g));
        }
        return null;
    }
    @Override
    public Boolean deleteGuidesToUser(Persona p) {
        if(p == null || p.getId() ==null)
            return false;
        personaGuidaRepository.findAll().stream()
                .filter(relation -> relation.getPersona().getId().equals(p.getId()) )
                .forEach( rel -> personaGuidaRepository.delete(rel));
        return true;
    }



}//end class
