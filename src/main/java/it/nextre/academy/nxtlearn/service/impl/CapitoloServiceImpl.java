package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.repository.CapitoloRepository;
import it.nextre.academy.nxtlearn.service.CapitoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CapitoloServiceImpl implements CapitoloService {
    @Autowired
    CapitoloService capitoloService;
    @Autowired
    CapitoloRepository capitoloRepository;
    @Override
    public Capitolo findById(Integer id) {
        Capitolo capitolo = capitoloRepository.findById(id).orElse(null);
        return capitolo;
    }
    @Override
    public List<Capitolo> getAll() {
        return capitoloRepository.findAll();
    }
    @Override
    public Capitolo newCapitolo(Capitolo c) {
        if (c != null && c.getId() == null) {
            c = capitoloRepository.save(c) ;
            return c;
        }
        return null;
    }
    @Override
    public Capitolo update(Capitolo c) {
        if (c != null && findById(c.getId()) != null) {
            return capitoloRepository.save(c);
        }
        return null;
    }
    @Override
    public Boolean deleteById(Integer id) {
        if (id != null && id > 0) {
            try {
                capitoloRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}//end class