package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.dto.GuidaDto;
import it.nextre.academy.nxtlearn.model.Guida;

import it.nextre.academy.nxtlearn.repository.AllegatoRepository;
import it.nextre.academy.nxtlearn.repository.CapitoloRepository;
import it.nextre.academy.nxtlearn.repository.GuidaRepository;
import it.nextre.academy.nxtlearn.repository.LezioneRepository;
import it.nextre.academy.nxtlearn.service.GuidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GuidaServiceImpl implements GuidaService {

    @Autowired
    GuidaRepository guidaRepository;

    @Autowired
    CapitoloRepository capitoloRepository;

    @Autowired
    LezioneRepository lezioneRepository;

    @Autowired
    AllegatoRepository allegatoRepository;

        
    @Override
    public Guida findById(Integer id) {
        Guida guide = guidaRepository.findById(id).orElse(null);
        return guide;
    }

    @Override
    public List<Guida> getAll() {
        return guidaRepository.findAll();
    }

    @Override
    public Guida newGuida(Guida g) {
        if (g != null && g.getId() == null) {
            g = guidaRepository.save(g);
            return g;
        }
        return null;
    }

    @Override
    public Guida update(Guida g) {
        if (g != null && findById(g.getId()) != null) {
            return guidaRepository.save(g);
        }
        return null;
    }

    public Boolean deleteById(Integer id) {
        if (id != null && id > 0) {
            try {
                guidaRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public GuidaDto toDto(Guida guida) {
        //versione 2 : il DTO stesso è responsabile del suo popolamento
        GuidaDto gdto = new GuidaDto(guida);
        gdto.caricaCapitoli(capitoloRepository, lezioneRepository, allegatoRepository);
        return gdto;
    }

    @Override
    public List<Guida> getLastTen() {
        return guidaRepository.findTop10ByOrderByDataCreazioneDesc();
    }


    @Override
    public List<Guida> findByNome(String nome) {
        List<Guida> guide=guidaRepository.findAllByNomeContaining(nome);
        return guide;
    }

}//end class