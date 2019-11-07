package it.nextre.academy.nxtlearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Persona implements Cloneable{
    private Integer id;
    private String nome;
    private String cognome;

    @Override
    public Persona clone() {
        return new Persona(this.id, this.nome, this.cognome);
    }
}//end class
