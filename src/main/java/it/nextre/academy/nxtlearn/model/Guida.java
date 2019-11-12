package it.nextre.academy.nxtlearn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "guida")
@Data
@NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(
        generator= ObjectIdGenerators.PropertyGenerator.class,
        property="id")
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

    /*
    //SCELTO DI FARE UNA TABELLA CON CAMPI EXTRA
    @JsonBackReference
    @ManyToMany(mappedBy = "guide")
    private List<Persona> persone;
    */

    // https://www.baeldung.com/jpa-cascade-types
    @OneToMany(mappedBy = "guida", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<PersonaGuida> persone;


}//end class
