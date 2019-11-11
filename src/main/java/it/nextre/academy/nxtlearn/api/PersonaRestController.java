package it.nextre.academy.nxtlearn.api;

import it.nextre.academy.nxtlearn.dto.PersonaDto;
import it.nextre.academy.nxtlearn.exception.BadRequestException;
import it.nextre.academy.nxtlearn.exception.NotFoundException;
import it.nextre.academy.nxtlearn.exception.PersonaNotFoundException;
import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persona")
public class PersonaRestController {


    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    PersonaService personaService;

    @GetMapping
    public List<Persona> getAll(){
        logger.debug("GET Persona.getAll()");
        return personaService.getPersone();
    }

    @GetMapping("/{id}")
    public PersonaDto getOne(@PathVariable("id") Integer id){
        logger.debug("GET Persona.getOne() with id: "+id);
        Persona tmp = personaService.getPersona(id);
        if (tmp!=null) {
            return personaService.toDto(tmp);
        } else
            throw new PersonaNotFoundException();
    }

    @PostMapping
    public Persona addOne(@RequestBody @Valid Persona p, BindingResult validator){
        logger.debug("POST Persona.addOne()");

        if (validator.hasErrors()){
            String errs = validator.getAllErrors()
                    .stream()
                    .map(e->e.getDefaultMessage())
                    .collect(Collectors.joining( ", " ));
            throw new BadRequestException(errs);
        }

        if (p!=null){
            p=personaService.newPersona(p);
            if (p==null){
                logger.warn("Invalid Persona datas");
                logger.debug("Persona datas: "+p);
                throw new BadRequestException();
                // throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        return p;
    }

    @PutMapping("/{id}")
    public Persona editOne(@RequestBody Persona p, @PathVariable("id") Integer id){
        logger.debug("PUT Persona.editOne() with id: "+id);
        if (p!=null && p.getId().equals(id)){
            Persona tmp = personaService.update(p);
            if (tmp!=null)
                return tmp;
            else
                throw new NotFoundException();
        }
        logger.warn("Incompatible ID");
        // throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        throw new BadRequestException();
    }

    @DeleteMapping("/{id}")
    public Map deleteById(@PathVariable("id") Integer id){
        logger.debug("DELETE Persona.deleteById() with id: "+id);
        Map<String, Object> risp = new HashMap<>();
        risp.put("result", personaService.deletePersonaById(id));
        return risp;
    }

}//end class
