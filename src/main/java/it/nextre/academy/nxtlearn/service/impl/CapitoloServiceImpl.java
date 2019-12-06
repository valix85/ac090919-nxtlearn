package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.dto.CapitoloDto;
import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.repository.AllegatoRepository;
import it.nextre.academy.nxtlearn.repository.CapitoloRepository;
import it.nextre.academy.nxtlearn.repository.LezioneRepository;
import it.nextre.academy.nxtlearn.service.CapitoloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CapitoloServiceImpl implements CapitoloService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CapitoloService capitoloService;
    @Autowired
    CapitoloRepository capitoloRepository;
    @Autowired
    LezioneRepository lezioneRepository;
    @Autowired
    AllegatoRepository allegatoRepository;



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

    @Override
    public List<Capitolo> findCapitoliByGuida(Guida g){
        logger.debug("Capitolo findCapitoliByGuida() "+g);
        if(g !=null) {
            List<Capitolo> capitoli = capitoloRepository.findAllByGuidaOrderByOrdineCapitolo(g);
            return capitoli;
        }
        else
            return new ArrayList<Capitolo>();
    }



    @Override
    public Capitolo save(Capitolo capitolo) {
        logger.debug("Capitolo save() "+capitolo);
        if (capitolo != null && capitolo.getId() == null) {
            return capitoloRepository.save(capitolo);
        }
        return null;
    }

    @Override
    public CapitoloDto toDto(Capitolo capitolo) {
        logger.debug("Capitolo toDto() "+capitolo);
        CapitoloDto tmp = new CapitoloDto(capitolo, lezioneRepository, allegatoRepository, false); // todo controllare nel front che non servono le lezioni
        return tmp;
    }


}//end class