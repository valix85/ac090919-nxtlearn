package it.nextre.academy.nxtlearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="persona")
public class Persona implements Cloneable{
    @Min(value=1, message = "ID non valido")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Nome non valido")
    @Size(min=2, message = "Almeno 2 caratteri per il nome")
    @Column(nullable = false)
    private String nome;

    @Size(min = 1,max=16,message = "Cognome compreso tra 1 e 16 lettere")
    @NotBlank(message = "Cognome non valido")
    @Column(nullable = false)
    private String cognome;

    @Override
    public Persona clone() {
        return new Persona(this.id, this.nome, this.cognome);
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
