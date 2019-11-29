package it.nextre.academy.nxtlearn.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.model.Lezione;
import it.nextre.academy.nxtlearn.repository.AllegatoRepository;
import it.nextre.academy.nxtlearn.repository.CapitoloRepository;
import it.nextre.academy.nxtlearn.repository.LezioneRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CapitoloDto {

    @Transient
    @JsonIgnore
    private Capitolo capitolo;


    private String nome;
    private List lezioni = new ArrayList<>();

    public CapitoloDto(Capitolo capitolo, LezioneRepository lezioneRepository, AllegatoRepository allegatoRepository){
        this.capitolo = capitolo;
        this.nome = capitolo.getNome();
        caricaLezioni(lezioneRepository, allegatoRepository);
    }

    public void caricaLezioni(LezioneRepository lezioneRepository, AllegatoRepository allegatoRepository){
        this.lezioni = lezioneRepository.findAllByCapitoloOrderByOrdineLezione(capitolo)
                .stream()
                .map(l -> new LezioneDto(l, allegatoRepository))
                .collect(Collectors.toList());
    }

}
