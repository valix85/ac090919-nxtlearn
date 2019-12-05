package it.nextre.academy.nxtlearn.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lezione")
public class Lezione extends BaseEntity {
    @Size(min = 1, max = 255, message = "Titolo lezione compreso tra 1 e 255 caratteri")
    @NotEmpty
    @NotBlank
    private String titolo;

    @Type(type = "text")
    private String descrizione;
    @Size(min = 1, max = 255, message = "Nome autore compreso tra 1 e 255 caratteri")
    @NotEmpty
    @NotBlank
    private String autore;

    @Type(type = "text")
    private String contenuto; //Pensarci sul not null o null

    private String url;

    @Min(0)
    private Integer ordineLezione;

    @JsonManagedReference
    @OneToOne//(mappedBy = "lezione")
    @JoinColumn(name = "capitolo_id")
    private Capitolo capitolo;

    @JsonManagedReference
    @OneToMany(mappedBy = "lezione", cascade = CascadeType.ALL)
    private List<Allegato> allegati;



    @Override
    public String toString() {
        return "Lezione{" +
                "titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", autore='" + autore + '\'' +
                ", contenuto='" + contenuto + '\'' +
                ", ordineLezione=" + ordineLezione +
                ", url='" + url + '\'' +
                ", id=" + id +
                ", dataCreazione=" + dataCreazione +
                ", dataModifica=" + dataModifica +
                '}';
    }
}//end class
