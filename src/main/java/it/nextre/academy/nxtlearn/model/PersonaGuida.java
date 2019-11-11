package it.nextre.academy.nxtlearn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.nextre.academy.nxtlearn.model.key.PersonaGuidaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="rel_persona_guida")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaGuida {

    @EmbeddedId
    private PersonaGuidaId id;

    @ManyToOne
    @MapsId("personaId")
    @JoinColumn(name="persona_id")
    @JsonBackReference
    private Persona persona;


    @ManyToOne
    @MapsId("guidaId")
    @JoinColumn(name="guida_id")
    @JsonBackReference
    private Guida guida;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inizio",updatable = false)
    Date dataInizio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_fine",insertable = false)
    Date dataFine;

}//end class
