package it.nextre.academy.nxtlearn.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.repository.AllegatoRepository;
import it.nextre.academy.nxtlearn.repository.CapitoloRepository;
import it.nextre.academy.nxtlearn.repository.LezioneRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuidaDto {

    @Transient
    @JsonIgnore
    private Guida g;

    private Integer id;
    private String nome;
    private String url;
    private String descrizione;
    private String image;
    private Map livello = new HashMap();
    private List capitoli = new ArrayList();

    public GuidaDto(Guida g){
        this.g=g;
        this.id=g.getId();
        this.nome=g.getNome();
        this.url=g.getUrl();
        this.descrizione=g.getDescrizione();
        this.image=g.getImagePath();
        if (g.getLivello()!=null) {
            livello.put("id", g.getLivello().getId());
            livello.put("descrizione", g.getLivello().getDescrizione());
            livello.put("difficolta", g.getLivello().getDifficolta());
        }
    }

    public void caricaCapitoli(CapitoloRepository capitoloRepository, LezioneRepository lezioneRepository, AllegatoRepository allegatoRepository, boolean soloIntestazione){
        this.capitoli = capitoloRepository.findAllByGuidaOrderByOrdineCapitolo(this.g)
                .stream()
                .map(c -> new CapitoloDto(c,lezioneRepository, allegatoRepository, soloIntestazione))
                .collect(Collectors.toList());
    }

}//end class
