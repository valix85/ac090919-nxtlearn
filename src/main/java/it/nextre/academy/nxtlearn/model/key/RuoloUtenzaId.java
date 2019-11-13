package it.nextre.academy.nxtlearn.model.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RuoloUtenzaId implements Serializable {

    @Column(name="utenza_id")
    private Integer utenzaId;
    @Column(name="ruolo_id")
    private Integer ruoloId;

}//end class
