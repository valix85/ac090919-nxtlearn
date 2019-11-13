package it.nextre.academy.nxtlearn.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ruolo")
public class Ruolo extends BaseEntity {

    @NotNull
    @Column(name="nome",unique = true)
    private String name;


}//end class