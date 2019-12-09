package it.nextre.academy.nxtlearn.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
    @JsonManagedReference
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utenza utenza = (Utenza) o;
        return getId().equals(utenza.getId()) &&
                Objects.equals(getEmail(), utenza.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }
}//end class
