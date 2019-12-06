package it.nextre.academy.nxtlearn.api;

import it.nextre.academy.nxtlearn.dto.CapitoloDto;
import it.nextre.academy.nxtlearn.dto.LezioneDto;
import it.nextre.academy.nxtlearn.exception.BadRequestException;
import it.nextre.academy.nxtlearn.exception.LezioneNotFoundException;
import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.model.Lezione;
import it.nextre.academy.nxtlearn.repository.LezioneRepository;
import it.nextre.academy.nxtlearn.service.CapitoloService;
import it.nextre.academy.nxtlearn.service.GuidaService;
import it.nextre.academy.nxtlearn.service.LezioneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/lezione")
public class LezioneRestController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LezioneService lezioneService;
    @Autowired
    CapitoloService capitoloService;
    @Autowired
    LezioneRepository lezioneRepository;
    @Autowired
    GuidaService guidaService;


    @GetMapping("/{id}")
    public Lezione getByID(@PathVariable("id") Integer id) {
        logger.info("LOG: getByIdLezione, id=" + id);
        Lezione tmp = lezioneService.findById(id);
        if (tmp != null) {
            return tmp;
        } else {
            throw new LezioneNotFoundException();
        }
    }

    /*
    // Troppo oneroso e mai richiesto, NON ATTIVARLO
    @GetMapping()
    public List<Lezione> getLezioni() {
        logger.info("Log: getLezioni()");
        return lezioneService.getAll();
    }
    */


    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public Lezione editOne(@RequestBody Lezione p, @PathVariable("id") Integer id) {
        logger.info("Log: update()");
        if (p != null && p.getId().equals(id)) {
            Lezione tmp = lezioneService.update(p);
            if (tmp != null) {
                return tmp;
            } else {
                throw new LezioneNotFoundException();
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/")
    public Lezione addOne(@RequestBody @Valid Lezione tmp, BindingResult validator) {
        logger.debug("LOG: addOne()");
        System.out.println(tmp);
        if (validator.hasErrors()) {
            logger.debug("LOG: validator.hasErrors()");
            String errs = validator.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new BadRequestException(errs);
        }
        if (tmp != null) {
            tmp = lezioneService.newLezione(tmp);
            return tmp;
        }
        throw new BadRequestException("Lezione non valida");

    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/new")
    public LezioneDto add(@RequestBody @Valid LezioneDto lezioneDto, BindingResult validator) {
        logger.debug("LOG: add()");
        if (validator.hasErrors()) {
            logger.debug("LOG: validator.hasErrors()");
            String errs = validator.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new BadRequestException(errs);
        }
        Lezione nuovaLezione = new Lezione();
        if (lezioneDto != null) {
            if(lezioneDto.getOrdineLezione() <0) {
                nuovaLezione.setOrdineLezione(capitoloService.findById(lezioneDto.getIdCapitolo()).getLezioni().size()+1);
            }
            nuovaLezione.setTitolo(lezioneDto.getTitolo());
            nuovaLezione.setAutore(lezioneDto.getAutore());
            nuovaLezione.setContenuto(lezioneDto.getContenuto());
            nuovaLezione.setCapitolo(capitoloService.findById(lezioneDto.getIdCapitolo()));
        }
        nuovaLezione=lezioneService.save(nuovaLezione);
        return lezioneService.toDto(nuovaLezione);
    }

    @Secured({"ROLE_ADMIN"})  // = jsr250 @RoleAllowed
    //@PreAuthorize("hasAuthority('CAN_DELETE')") //uso i ruoli
    @DeleteMapping("/{id}")
    public Lezione deleteById(@PathVariable("id") Integer id) {
        logger.info("LOG: deleteById, id=" + id);
        Lezione tmp = lezioneService.findById(id);
        if (tmp != null) {
            lezioneService.deleteById(id);
            return tmp;
        } else {
            throw new LezioneNotFoundException();
        }
    }


    @GetMapping("lezione/capitolo/{id}")
    public List<Lezione> getAllLezioniByCapitoloId(@PathVariable("id") Integer id) {
        logger.info("Log: getAllLezionebyCapitolo()");
        return lezioneService.findLezioneByGuidaCapitolo(capitoloService.findById(id));
    }
}//end class