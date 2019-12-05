package it.nextre.academy.nxtlearn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.nextre.academy.nxtlearn.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "allegato")
public class Allegato extends BaseEntity {
    @Size(min=1,max=255,message = "Nome capitolo compreso tra 1 e 255 caratteri")
    @NotEmpty
    @NotBlank
    private String nomeFileOrigine;

    @Size(min=1,max=255,message = "Nome capitolo compreso tra 1 e 255 caratteri")
    @NotEmpty
    @NotBlank
    private String nomeFileNostro;

    @Min(1)
    private Integer ordineAllegato;

    @Type(type="text") // LONGTEXT in mySQL
    // @Lob
    private String descrizioneAllegato;

    @OneToOne
    @JoinColumn(name="lezione_id")
    @JsonBackReference
    private Lezione lezione;

    @Override
    public String toString() {
        return "Allegato{" +
                "nomeFileOrigine='" + nomeFileOrigine + '\'' +
                ", nomeFileNostro='" + nomeFileNostro + '\'' +
                ", ordineAllegato=" + ordineAllegato +
                ", descrizioneAllegato='" + descrizioneAllegato + '\'' +
                ", id=" + id +
                ", dataCreazione=" + dataCreazione +
                ", dataModifica=" + dataModifica +
                '}';
    }
}//end class
