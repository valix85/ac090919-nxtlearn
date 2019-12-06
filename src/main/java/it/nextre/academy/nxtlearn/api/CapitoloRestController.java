package it.nextre.academy.nxtlearn.api;

import it.nextre.academy.nxtlearn.dto.CapitoloDto;
import it.nextre.academy.nxtlearn.dto.GuidaDto;
import it.nextre.academy.nxtlearn.exception.BadRequestException;
import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.repository.LezioneRepository;
import it.nextre.academy.nxtlearn.service.CapitoloService;
import it.nextre.academy.nxtlearn.service.GuidaService;
import it.nextre.academy.nxtlearn.service.LezioneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/capitolo")
public class CapitoloRestController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LezioneService lezioneService;
    @Autowired
    CapitoloService capitoloService;
    @Autowired
    LezioneRepository lezioneRepository;
    @Autowired
    GuidaService guidaService;

    @GetMapping("guida/{id}")
    public List<Capitolo> getCapitoloByGuida(@PathVariable("id") Integer id) {
        logger.info("Log: getCapitoli()");
        return capitoloService.findCapitoliByGuida(guidaService.findById(id));
    }

    @GetMapping("/{id}")
    public Capitolo getCapitoloById(@PathVariable("id") Integer id) {
        logger.info("LOG: getById, id=" + id);
        Capitolo tmp = capitoloService.findById(id);
        return tmp;
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public CapitoloDto addOne(@RequestBody @Valid CapitoloDto capDto, BindingResult validator) {
        logger.debug("LOG: CapitoloDtoaddOne()");
        System.out.println(capDto);
        if (validator.hasErrors()) {
            logger.debug("LOG: validator.hasErrors()");
            String errs = validator.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new BadRequestException(errs);
        }
        Capitolo c = new Capitolo();
        if (capDto != null) {
            if(capDto.getOrdineCapitolo() <0) {
                c.setOrdineCapitolo(guidaService.findById(capDto.getIdGuida()).getCapitoli().size()+1);
            }
            c.setNome(capDto.getNome());
            c.setLezioni(capDto.getLezioni());
            c.setGuida(guidaService.findById(capDto.getIdGuida()));
            System.out.println("C: " + c);
        }
        c = capitoloService.save(c);
        return capitoloService.toDto(c);
    }
}//end class