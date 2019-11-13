package it.nextre.academy.nxtlearn.model;

import it.nextre.academy.nxtlearn.model.key.RuoloUtenzaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
* questa classe è necessaria per generare la primary key sulla relazione utenza_ruolo, altrimenti sarebbero presenti solamente le due chiavi esterne
* */


@Entity
@Table(name = "rel_utenza_ruolo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuoloUtenza {

    @EmbeddedId
    RuoloUtenzaId id;

    @ManyToOne
    @MapsId("utenza_id")
    @JoinColumn(name = "utenza_id")
    private Utenza utenza;

    @ManyToOne
    @MapsId("ruolo_id")
    @JoinColumn(name = "ruolo_id")
    private Ruolo ruolo;

    public RuoloUtenza(Utenza utenza, Ruolo ruolo) {
        this.utenza = utenza;
        this.ruolo = ruolo;
    }
}//end class
