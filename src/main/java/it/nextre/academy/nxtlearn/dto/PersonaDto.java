package it.nextre.academy.nxtlearn.dto;

import it.nextre.academy.nxtlearn.model.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {
    private Integer id;
    private String nome;
    private String cognome;
    private Set guide = new HashSet();
}//end class
