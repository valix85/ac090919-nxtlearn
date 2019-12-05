package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.dto.UtenzaDto;
import it.nextre.academy.nxtlearn.model.Utenza;
import it.nextre.academy.nxtlearn.repository.UtenzaRepository;
import it.nextre.academy.nxtlearn.service.UtenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenzaServiceImpl implements UtenzaService {

    @Autowired
    UtenzaRepository utenzaRepository;

    @Override
    public Utenza create(Utenza ut) {
        return utenzaRepository.save(ut);
    }

    @Override
    public Utenza findByEmail(String email) {
        return utenzaRepository.findByEmail(email);
    }


    @Override
    public List<Utenza> getUtenze() {
        return utenzaRepository.findAll();
    }
    @Override
    public Utenza getUtenza(Integer id) {
        return utenzaRepository.getOne(id);
    }
    @Override
    public UtenzaDto toDto(Utenza u) {
        UtenzaDto dto = new UtenzaDto();
        dto.setId(u.getId());
        dto.setNome(u.getPersona().getNome());
        dto.setCognome(u.getPersona().getCognome());
        dto.setEmail(u.getEmail());
        dto.setRuoli(u.getRuoli().iterator().next().getName());
        return dto;
    }
    @Override
    public Utenza updateUtenza(Utenza u) {
        if (u!=null && getUtenza(u.getId())!=null) {
            return utenzaRepository.save(u);
        }
        return null;
    }


}//end class
