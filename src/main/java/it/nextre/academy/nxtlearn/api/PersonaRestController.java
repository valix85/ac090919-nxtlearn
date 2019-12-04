package it.nextre.academy.nxtlearn.api;

import it.nextre.academy.nxtlearn.config.CustomUserDetails;
import it.nextre.academy.nxtlearn.dto.PersonaDto;
import it.nextre.academy.nxtlearn.exception.BadRequestException;
import it.nextre.academy.nxtlearn.exception.NotAllowedException;
import it.nextre.academy.nxtlearn.exception.NotFoundException;
import it.nextre.academy.nxtlearn.exception.PersonaNotFoundException;
import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.model.PersonaGuida;
import it.nextre.academy.nxtlearn.service.PersonaGuidaService;
import it.nextre.academy.nxtlearn.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
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

    @Autowired
    PersonaGuidaService personaGuidaService;

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



    // todo attendo modifica frustecchi
    @Secured({"ROLE_SIMPLEUSER", "ROLE_ADMIN"})  // = jsr250 @RoleAllowed
    @PostMapping("/subscribe")
    public MyResponse iscrizioneGuida(@RequestBody Map<String, Object> body, Principal user) {
        Integer idUtenteLoggato = -1;
        Boolean isAdmin = false;
        try {
            CustomUserDetails cud =
                    (CustomUserDetails) (((UsernamePasswordAuthenticationToken) user).getPrincipal());
            idUtenteLoggato = cud.getId();
            isAdmin = cud.getAuthorities().stream().map(ga -> ga.getAuthority())
                    .peek(System.out::println)
                    .filter(ruolo -> ruolo.equals("ROLE_ADMIN"))
                    .findFirst().isPresent();
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
        // recupero l'ID dello user che esegue la richiesta
        Integer idUtenteRichiesta = -1;
        try {
            idUtenteRichiesta = (Integer) body.get("utenzaId");
            if (idUtenteRichiesta==null)
                throw new IllegalArgumentException("utenzaId non presente");
            // todo valutare che se non è un numero devo bloccare tutto, e non settarlo a -1
            System.out.println(idUtenteRichiesta);
        }catch (IllegalArgumentException ex){
            //se non c'è resta -1
            idUtenteRichiesta = -1;
        }
        //controllo l'id della guida
        Integer idGuidaRichiesta = -1;
        try {
            idGuidaRichiesta = (Integer) body.get("guidaId");
            if (idGuidaRichiesta<=0)
                throw new BadRequestException();
        }catch (Exception ex){
            throw new BadRequestException("Id guida non valido");
        }

        // String email = (String)body.get("email");
        PersonaGuida risp = null;
        if (idUtenteLoggato > 0 && idUtenteRichiesta != null && idUtenteRichiesta > 0 && idGuidaRichiesta != null && idGuidaRichiesta > 0 && isAdmin) {
            // OK I DATI SONO VALIDI, SONO ADMIN, per fare un'associazion terze parti
            risp = personaGuidaService.addRelation(idUtenteRichiesta, idGuidaRichiesta);
            return new MyResponse(HttpStatus.OK,risp);
        }else if (idUtenteLoggato > 0 && idUtenteRichiesta.equals(-1)){
            // devo registrare me stesso
            risp = personaGuidaService.addRelation(idUtenteLoggato, idGuidaRichiesta);
            return new MyResponse(HttpStatus.OK,risp);
        }else{
            throw new NotAllowedException("Permessi insufficienti per eseguire l'operazione");
        }
    }


    @GetMapping("/guida/{idGuida}")
    public boolean isIscritto(@PathVariable("idGuida") Integer idGuida){
        logger.info("CONTROLLO GUIDA: " + idGuida);
        boolean isIscritto = personaGuidaService.checkRelation(idGuida);
        System.out.println(isIscritto);
        if (isIscritto)
            return personaGuidaService.checkRelation(idGuida);
        else
            throw new NotFoundException("Guida non associata all'utente corrente");
    }

}//end class
