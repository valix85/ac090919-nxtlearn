package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.dto.LezioneDto;
import it.nextre.academy.nxtlearn.exception.BadRequestException;
import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Lezione;
import it.nextre.academy.nxtlearn.repository.AllegatoRepository;
import it.nextre.academy.nxtlearn.repository.LezioneRepository;
import it.nextre.academy.nxtlearn.service.LezioneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LezioneServiceImpl implements LezioneService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LezioneRepository lezioneRepository;

    public Lezione findById(Integer id) {
        Lezione lezione = lezioneRepository.findById(id).orElse(null);
        return lezione;
    }

    @Autowired
    AllegatoRepository allegatoRepository;
    public LezioneDto toDto(Lezione lezione){
        LezioneDto tmp = new LezioneDto(lezione, allegatoRepository);
        return tmp;
    }

    /*
    @Override
    public List<Lezione> getAll() {
        return lezioneRepository.findAll();
    }
    */

    @Override
    public List<Lezione> findLezioneByGuidaCapitolo(Capitolo c) {
        logger.debug("LezioneService findLezioneByGuidaCapitolo() capitolo id:" + c.getId());
        if(c !=null) {
            List<Lezione> lezioni = lezioneRepository.findAllByCapitoloOrderByOrdineLezione(c);

            return lezioni;
        }
        else
            return new ArrayList<Lezione>();
    }


    @Override
    public Boolean deleteById(Integer id) {
        logger.debug("LezioneService deleteById():" + id);
        if (id != null && id > 0) {
            try {
                lezioneRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
    @Override
    public Lezione newLezione(Lezione c) {
        logger.debug("LezioneService new():" + c);
        c = lezioneRepository.save(c);
        return c;
    }
    @Override
    public Lezione update(Lezione c) {
        logger.debug("LezioneService update():" + c);
        if (c != null && findById(c.getId()) != null) {
            return lezioneRepository.save(c);
        }
        throw new BadRequestException("id lezione non presente");
    }
    public Lezione save(Lezione c) {
        logger.debug("LezioneService save():" + c);
        if (c != null && c.getId() == null) {
            return lezioneRepository.save(c);
        }
        throw new BadRequestException("id lezione già presente");
    }




}//end class
