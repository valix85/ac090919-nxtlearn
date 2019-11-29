package it.nextre.academy.nxtlearn.dto;

import it.nextre.academy.nxtlearn.model.Allegato;
import it.nextre.academy.nxtlearn.model.Lezione;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AllegatoDto {

    private String nomeFileOrigine;
    private String nomeFileNostro;
    private String descrizioneAllegato;

    public AllegatoDto(Allegato allegato){
        this.nomeFileNostro = allegato.getNomeFileNostro();
        this.nomeFileOrigine = allegato.getNomeFileOrigine();
        this.descrizioneAllegato = allegato.getDescrizioneAllegato();
    }

}
