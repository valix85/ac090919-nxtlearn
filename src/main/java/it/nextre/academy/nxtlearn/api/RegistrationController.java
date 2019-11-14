package it.nextre.academy.nxtlearn.api;

import it.nextre.academy.nxtlearn.dto.NuovoUtenteDto;
import it.nextre.academy.nxtlearn.dto.PersonaDto;
import it.nextre.academy.nxtlearn.exception.PersonaAlreadyExistsException;
import it.nextre.academy.nxtlearn.exception.RuoloNotFoundException;
import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.model.Ruolo;
import it.nextre.academy.nxtlearn.model.RuoloUtenza;
import it.nextre.academy.nxtlearn.model.Utenza;
import it.nextre.academy.nxtlearn.service.PersonaService;
import it.nextre.academy.nxtlearn.service.RuoloService;
import it.nextre.academy.nxtlearn.service.UtenzaService;
import it.nextre.academy.nxtlearn.service.impl.CustomUserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonaService personaService;

    @Autowired
    UtenzaService utenzaService;

    @Autowired
    RuoloService ruoloService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping
    public PersonaDto doRegistration(@RequestBody @Valid NuovoUtenteDto nuovoUtente, BindingResult result){
        logger.debug("POST RegistrationController.doRegistration()");
        System.out.println(nuovoUtente);

        if (utenzaService.findByEmail(nuovoUtente.getEmail())!=null){
            throw new PersonaAlreadyExistsException();
        }

        Persona tmp = new Persona();
        tmp.setNome(nuovoUtente.getNome());
        tmp.setCognome(nuovoUtente.getCognome());
        tmp = personaService.newPersona(tmp);

        Utenza utenza = new Utenza();
        utenza.setEmail(nuovoUtente.getEmail());
        utenza.setPassword(passwordEncoder.encode(nuovoUtente.getPassword()));
        utenza.setPersona(tmp);


        Ruolo ruolo = null;
        try {
            ruolo = ruoloService.getByName("ROLE_SIMPLEUSER");
        } catch (RuoloNotFoundException e){
            logger.debug("Creazione nuovo ruolo in fase di registrazione");
            ruolo = ruoloService.save(new Ruolo("ROLE_SIMPLEUSER"));
        }
        utenza.setRuoli(Arrays.asList(ruolo));
        utenza = utenzaService.create(utenza);

        System.out.println(utenza);

        return personaService.toDto(tmp);
    }

}//end class
