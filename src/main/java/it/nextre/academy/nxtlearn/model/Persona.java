package it.nextre.academy.nxtlearn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="persona")

@JsonIdentityInfo(
        generator=ObjectIdGenerators.PropertyGenerator.class,
        property="id")
public class Persona implements Cloneable{
    @Min(value=1, message = "ID non valido")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome non valido")
    @Size(min=2, max=128, message = "Nome compreso tra 2 e 128 caratteri")
    @Column(nullable = false)
    private String nome;

    @Size(min = 1,max=128,message = "Cognome compreso tra 1 e 128 caratteri")
    @NotBlank(message = "Cognome non valido")
    @Column(nullable = false, length = 128) // length ignorato se usato @size
    private String cognome;



    /*
    //SCELTO DI FARE UNA TABELLA CON CAMPI EXTRA
    //che guide la persona sta seguendo:
    // https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
    @ManyToMany
    @JoinTable(name = "rel_persona_guida",
            joinColumns = @JoinColumn(
                    //nome colonna DB
                    name = "persona_id"
            ),
            //nome proprieta'
            inverseJoinColumns = @JoinColumn(
                    name = "guida_id"
            ),
            // https://stackoverflow.com/questions/3473978/manytomany-relationship-using-jpa-with-hibernate-provider-is-not-creating-primar
            uniqueConstraints = {@UniqueConstraint(name="unique_persona_guida", columnNames = {"persona_id","guida_id"})}
    )
    private Set<Guida> guide;
     */


    @OneToMany(mappedBy = "persona")
    @JsonManagedReference
    private List<PersonaGuida> guide;


    @OneToOne(mappedBy = "persona")
    //@PrimaryKeyJoinColumn
    private Utenza utenza;


    @Override
    public Persona clone() {
        return new Persona(this.id, this.nome, this.cognome, this.guide, this.utenza);
    }

    /*
    * At first Spring will use setter-method to set value of property. And for validate value Spring will get it with getter-method. That means, you can trim value in setter-method for prepare it to validation.
    * https://stackoverflow.com/questions/42921984/javax-validation-size-trim-whitespace
    * */

    public void setNome(String nome) {
        this.nome = nome.strip();
    }

    public void setCognome(String cognome) {
        this.cognome = cognome.strip();
    }
/*
    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                '}';
    }

 */
}//end class
