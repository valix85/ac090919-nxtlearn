package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.myutils.DummyData;
import it.nextre.academy.nxtlearn.repository.PersonaRepository;
import it.nextre.academy.nxtlearn.repository.PersonaRepositoryDB;
import it.nextre.academy.nxtlearn.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public Persona getRandom() {
        Persona p = new Persona();
        p.setNome(DummyData.getName());
        p.setCognome(DummyData.getSurname());
        p = personaRepository.save(p);
        return p;
    }

    @Override
    public List<Persona> getRandoms(Integer numero) {
        List<Persona> tmp = new ArrayList<>();
        for (int i = 0; i < numero; i++) {
            tmp.add(getRandom());
        }//end for
        return tmp;
    }

    @Override
    public List<Persona> getPersone() {
        return personaRepository.getAll();
    }

    @Override
    public Persona getPersona(Integer id) {
        if (id!=null && id>0){
            return personaRepository.findById(id);
        }
        return null;
    }

    @Override
    public Persona newPersona(Persona p) {
        if (p!=null && p.getId()==null){
            return personaRepository.save(p);
        }
        return null;
    }

    @Override
    public boolean deletePersonaById(Integer id) {
        if (id != null && id >=0){
            return personaRepository.deleteById(id);
        }
        return false;
    }

    @Override
    public Persona update(Persona p) {
        if (p!=null && getPersona(p.getId())!=null){
            return personaRepository.save(p);
        }
        return null;
    }
}//end class
