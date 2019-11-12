package it.nextre.academy.nxtlearn.service;
import it.nextre.academy.nxtlearn.dto.GuidaDto;
import it.nextre.academy.nxtlearn.model.Guida;
import java.util.List;
public interface GuidaService {
    Guida findById(Integer id);
    List<Guida> getAll();
    Guida newGuida(Guida g);
    Guida update(Guida g);
    Boolean deleteById(Integer id);
    GuidaDto toDto(Guida guida);
}