package it.nextre.academy.nxtlearn.dto;

import it.nextre.academy.nxtlearn.model.Lezione;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LezioneDtoShort {
    private String titolo;
    private Integer id;
    public LezioneDtoShort(Lezione lezione){
        this.id = lezione.getId();
        this.titolo = lezione.getTitolo();
    }
}// end class