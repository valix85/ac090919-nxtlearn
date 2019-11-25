package it.nextre.academy.nxtlearn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtRequestDto  implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    private String utente;
    private String pwd;

}//end class
