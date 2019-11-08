package it.nextre.academy.nxtlearn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "guida")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Guida extends BaseEntity{


    @Size(min=1,max=255,message = "Nome guida compreso tra 1 e 255 caratteri")
    @NotEmpty @NotBlank
    private String nome;

    @Size(max=255,message = "URL guida max 255 caratteri")
    @Null
    private String url;

    @Type(type="text") // LONGTEXT in mySQL
    // @Lob
    @Null
    private String descrizione;

    @Size(max=255,message = "Path immagine max 255 caratteri")
    @Null
    private String imagePath;

    @ManyToOne
    private Livello livello;

    @JsonBackReference
    @ManyToMany(mappedBy = "guide")
    private List<Persona> persone;


}//end class
