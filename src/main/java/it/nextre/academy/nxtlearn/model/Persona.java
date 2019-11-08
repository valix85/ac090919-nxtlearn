package it.nextre.academy.nxtlearn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="persona")
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

    //chi mi ha mangiato
    @ManyToMany
    @JoinTable(name = "rel_persona_guida",
            joinColumns = @JoinColumn(
                    //nome colonna DB
                    name = "persona_id"
            ),
            //nome proprieta'
            inverseJoinColumns = @JoinColumn(
                    name = "guida_id"
            )
    )
    private List<Guida> guide;

    @Override
    public Persona clone() {
        return new Persona(this.id, this.nome, this.cognome, this.guide);
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
}//end class
