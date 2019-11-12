package it.nextre.academy.nxtlearn.dto;

import it.nextre.academy.nxtlearn.model.Guida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuidaDto {

    private Integer id;
    private String nome;
    private String url;
    private String descrizione;
    private String image;
    private Map livello = new HashMap();

    public GuidaDto(Guida g){
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

}//end class
