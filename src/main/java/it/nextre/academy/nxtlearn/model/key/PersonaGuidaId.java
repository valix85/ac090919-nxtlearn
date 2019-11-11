package it.nextre.academy.nxtlearn.model.key;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class PersonaGuidaId implements Serializable {

    @Column(name="persona_id")
    private Integer personaId;

    @Column(name="guida_id")
    private Integer guidaId;

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaGuidaId that = (PersonaGuidaId) o;
        return personaId.equals(that.personaId) &&
                guidaId.equals(that.guidaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personaId, guidaId);
    }
    */

}//end class
