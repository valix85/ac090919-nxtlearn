package it.nextre.academy.nxtlearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "livello")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livello extends BaseEntity {
    @NotBlank  //todo testare carattere di invio se viene trimmato oppure no
    @NotEmpty
    private String descrizione;

    @Min(value = 1, message = "Descrizione valore minimo 1")
    @Max(value = 3, message = "Descrizione valore massimo 3")
    @Column(columnDefinition="INTEGER(1)")
    private Integer difficolta;

}//end class
