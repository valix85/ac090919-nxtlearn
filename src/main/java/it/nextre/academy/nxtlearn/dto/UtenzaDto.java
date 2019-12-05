package it.nextre.academy.nxtlearn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenzaDto {

    private Integer id;
    private String email;
    private String nome;
    private String cognome;
    private String ruoli;

}//end class


