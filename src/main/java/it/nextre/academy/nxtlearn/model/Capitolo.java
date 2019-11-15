package it.nextre.academy.nxtlearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "capitolo")
public class Capitolo extends BaseEntity {
    @Size(min=1,max=255,message = "Nome capitolo compreso tra 1 e 255 caratteri")
    @NotEmpty
    @NotBlank
    private String nome;
    @Type(type="text") // LONGTEXT in mySQL
    // @Lob
    private String url;
    @NotEmpty
    @NotBlank
    private Integer ordineCapitolo;
    @OneToMany(mappedBy = "capitolo")
    private List<Lezione> lezioni;
    @OneToOne
    @JoinColumn(name="guida_id")
    private Guida guida;
} //end class
