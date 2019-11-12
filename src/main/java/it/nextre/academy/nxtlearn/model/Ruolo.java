package it.nextre.academy.nxtlearn.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ruolo")
public class Ruolo extends BaseEntity {

    @NotNull
    @Column(name="nome", unique = true)
    private String name;

}//end class