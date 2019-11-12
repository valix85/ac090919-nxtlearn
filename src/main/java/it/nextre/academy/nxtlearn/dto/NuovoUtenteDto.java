package it.nextre.academy.nxtlearn.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NuovoUtenteDto {

    //todo add jsr-380 and validate it into controller

    private String email;
    private String password;
    private String nome;
    private String cognome;

}//end class
