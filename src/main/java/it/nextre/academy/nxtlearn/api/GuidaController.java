package it.nextre.academy.nxtlearn.api;

import it.nextre.academy.nxtlearn.dto.GuidaDto;
import it.nextre.academy.nxtlearn.exception.BadRequestException;
import it.nextre.academy.nxtlearn.exception.GuidaNotFoundException;
import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.service.GuidaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guida")
public class GuidaController {

    @Autowired
    GuidaService guidaService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Secured({"ROLE_SIMPLEUSER"})  // = jsr250 @RoleAllowed
    @GetMapping("/{id}")
    public GuidaDto getByID(@PathVariable("id") Integer id) {
        logger.info("LOG: getById, id=" + id);
        Guida tmp = guidaService.findById(id);
        if (tmp != null) {
            return guidaService.toDto(tmp);
        } else {
            throw new GuidaNotFoundException();
        }
    }

    @GetMapping
    public List<Guida> getGuide() {
        logger.info("Log: getGuide()");
        return guidaService.getAll();
    }

    @PutMapping("/{id}")
    public Guida editOne(@RequestBody Guida p, @PathVariable("id") Integer id) {
        logger.info("Log: update()");
        if (p != null && p.getId().equals(id)) {
            Guida tmp = guidaService.update(p);
            if (tmp != null) {
                return tmp;
            } else {
                throw new GuidaNotFoundException();
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public Guida addOne(@RequestBody @Valid Guida tmp, BindingResult validator) {
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
            guidaService.newGuida(tmp);
        }
        return tmp;
    }



    @Secured({"ROLE_ADMIN"})  // = jsr250 @RoleAllowed
    //@PreAuthorize("hasAuthority('CAN_DELETE')") //uso i ruoli
    @DeleteMapping("/{id}")
    public Guida deleteById(@PathVariable("id") Integer id) {
        logger.info("LOG: deleteById, id=" + id);
        Guida tmp = guidaService.findById(id);
        if (tmp != null) {
            guidaService.deleteById(id);
            return tmp;
        } else {
            throw new GuidaNotFoundException();
        }
    }
}//end class