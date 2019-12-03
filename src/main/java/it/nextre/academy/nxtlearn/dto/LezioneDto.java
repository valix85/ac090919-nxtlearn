package it.nextre.academy.nxtlearn.dto;

import it.nextre.academy.nxtlearn.model.Allegato;
import it.nextre.academy.nxtlearn.model.Lezione;
import it.nextre.academy.nxtlearn.repository.AllegatoRepository;
import it.nextre.academy.nxtlearn.repository.LezioneRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LezioneDto {

    private String titolo;
    private String descrizione;
    private String autore;
    private String contenuto;
    private List allegati = new ArrayList();
    private Integer id;
    private Date dataCreazione;

    public LezioneDto(Lezione lezione, AllegatoRepository allegatoRepository){
        this.id=lezione.getId();
        this.dataCreazione=lezione.getDataCreazione();
        this.titolo = lezione.getTitolo();
        this.descrizione = lezione.getDescrizione();
        this.autore = lezione.getAutore();
        this.contenuto = lezione.getContenuto();
        this.allegati = allegatoRepository.findAllByLezioneOrderByOrdineAllegato(lezione)
                .stream()
                .map(AllegatoDto::new)
                .collect(Collectors.toList());
    }

}
