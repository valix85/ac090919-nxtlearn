package it.nextre.academy.nxtlearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
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
    @Null
    @Type(type = "text")
    private String descrizione;
    @Size(min = 1, max = 255, message = "Nome autore compreso tra 1 e 255 caratteri")
    @NotEmpty
    @NotBlank
    private String autore;
    @Null
    @Type(type = "text")
    private String contenuto; //Pensarci sul not null o null
    @NotEmpty
    @NotBlank
    private Integer ordineLezione;
    @OneToOne//(mappedBy = "lezione")
    @JoinColumn(name = "capitolo_id")
    private Capitolo capitolo;
    @OneToMany(mappedBy = "lezione")
    private List<Allegato> allegati;
}//end class
