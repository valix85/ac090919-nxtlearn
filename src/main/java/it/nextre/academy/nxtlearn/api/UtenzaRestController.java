package it.nextre.academy.nxtlearn.api;

import it.nextre.academy.nxtlearn.dto.GuidaDto;
import it.nextre.academy.nxtlearn.dto.UtenzaDto;
import it.nextre.academy.nxtlearn.exception.BadRequestException;
import it.nextre.academy.nxtlearn.exception.PersonaNotFoundException;
import it.nextre.academy.nxtlearn.model.*;
import it.nextre.academy.nxtlearn.service.GuidaService;
import it.nextre.academy.nxtlearn.service.PersonaGuidaService;
import it.nextre.academy.nxtlearn.service.RuoloService;
import it.nextre.academy.nxtlearn.service.UtenzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utenza")
public class UtenzaRestController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UtenzaService utenzaService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RuoloService ruoloService;
    @Autowired
    GuidaService guidaService;
    @Autowired
    PersonaGuidaService personaGuidaService;
    @GetMapping
    public List<Utenza> getAll() {
        logger.debug("GET method -> UtenzaRestController.getAll()");
        return utenzaService.getUtenze();
    }
    @GetMapping("/dto")
    public List<UtenzaDto> getAllDto() {
        logger.debug("GET method -> UtenzaRestController.getAllDto()");
        List<UtenzaDto> dtos = new ArrayList<>();
        for (Utenza user : utenzaService.getUtenze()) {
            dtos.add(utenzaService.toDto(user));
        }
        System.out.println(dtos);
        return dtos;
    }
    @GetMapping("/{id}")
    public UtenzaDto getOne(@PathVariable("id") Integer id) {
        logger.debug("GET method -> UtenzaRestController.getOne() with id: " + id);
        Utenza tmp = utenzaService.getUtenza(id);
        System.out.println(tmp);
        if (tmp != null) {
            return utenzaService.toDto(tmp);
        } else
            throw new PersonaNotFoundException();
    }
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/updatePwd")
    public Boolean updatePwd(@RequestBody HashMap<String, Object> ut, BindingResult result) {
        logger.debug("POST method -> UtenzaRestController.updatePwd() update ut = " + ut);
        String username = (String) ut.get("email");
        String password = (String) ut.get("password");
        String adminpwd = (String) ut.get("adminpwd");
        String adminmail =(String) ut.get("adminmail");
        if (result.hasErrors()) {
            String errs = result.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new BadRequestException(errs);
        }
        Utenza logged = utenzaService.findByEmail(adminmail);
        Utenza uToUpdate = utenzaService.findByEmail(username);
        if (!passwordEncoder.matches(adminpwd, logged.getPassword())) {
            System.out.println("Password dell'utente errata");
            return false;
        }
        uToUpdate.setPassword(passwordEncoder.encode(password));
        return utenzaService.updateUtenza(uToUpdate) != null;
    }
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/updateRole")
    public Boolean updateRole(@RequestBody HashMap<String, Object> ut, BindingResult result) {
        logger.debug("POST method -> UtenzaRestController.updateRole() update ut = " + ut);
        String username = (String) ut.get("email");
        String role = (String) ut.get("role");
        if (result.hasErrors()) {
            String errs = result.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new BadRequestException(errs);
        }
        Utenza uToUpdate = utenzaService.findByEmail(username);
        Ruolo newRole = ruoloService.getByName(role);
        List<Ruolo> list = new ArrayList<>();
        list.add(newRole);  // mi dava problemi nel salvataggio tramite uToUpdate.setRuoli(Arrays.asList(newRole));
        //stackoverflowando ho trovato questa soluzione
        uToUpdate.setRuoli(list);
        Utenza newUser = utenzaService.updateUtenza(uToUpdate);
        return utenzaService.updateUtenza(uToUpdate) != null;
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/roles")
    public List<String> getAllRoles() {
        logger.debug("GET method -> UtenzaRestController.getAllRoles()");
        List<String> cleanRoles = new ArrayList<>();
        this.ruoloService.getAll().stream()
                .map((r) -> r.getName())
                .forEach(r -> {
                    r = r.replace("ROLE_", "");
                    cleanRoles.add(r);
                });
        System.out.println(Arrays.toString(cleanRoles.toArray()));
        return cleanRoles;
    }
    @PostMapping("/myGuides")
    public List<GuidaDto> getGuidesByUser(@RequestBody String email) {
        logger.debug("GET method -> UtenzaRestController.getGuideByUser() with email: " + email);
        List<GuidaDto> usersGuide = new ArrayList<>();
        Utenza u = utenzaService.findByEmail(email);
        personaGuidaService.getGuideByUser(u.getPersona()).stream()
                .forEach(guida -> usersGuide.add(guidaService.toDto(guida,false))); // todo controllare che funzioni
        return usersGuide;
    }
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/upGuideToUser")
    public Boolean updateGuidesToUser(@RequestBody HashMap<String, Object> guides) {
        logger.debug("POST method -> UtenzaRestController.updateGuidesToUser");
        List<Integer> guide = (List<Integer>) guides.get("guide");
        String username = (String) guides.get("email");
        Persona p = utenzaService.findByEmail(username).getPersona();
        List<PersonaGuida> result = new ArrayList<>();
        System.out.println(personaGuidaService.deleteGuidesToUser(p));
        if(!personaGuidaService.deleteGuidesToUser(p)){
            logger.error("Errore nella cancellazione guide di "+username);
            return false;
        }
        guide.forEach(idGuida -> {
            Guida toAdd = guidaService.findById(idGuida);
            result.add(personaGuidaService.newRelation(p, toAdd));
            System.out.println("RelazioneAggiunta!");
        });
        for (PersonaGuida relation : result) {  //verifico l'aggiunta sia stata eseguita con successo
            if (relation == null)
                return false;
        }
        return true;
    }
}//end class