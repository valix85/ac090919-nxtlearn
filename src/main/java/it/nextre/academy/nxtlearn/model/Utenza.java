package it.nextre.academy.nxtlearn.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utenza")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Utenza {

    @Id
    @Column(name="id")
    Integer id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")   //same name as id @Column
    private Persona persona;

    @Column(unique = true)
    private String email;
    private String password;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="rel_utenza_ruolo",
            joinColumns = @JoinColumn(name = "utenza_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="ruolo_id", referencedColumnName = "id")
    )
    private Collection<Ruolo> ruoli;

    @Override
    public String toString() {
        return "Utenza{" +
                "id=" + id +
                ", email='" + email + '\'' +
                // ", password='" + password + '\'' + //resta traccia nei log, mai stampare la password
                '}';
    }
}//end class
