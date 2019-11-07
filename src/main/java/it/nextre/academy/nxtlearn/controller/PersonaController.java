package it.nextre.academy.nxtlearn.controller;

import it.nextre.academy.nxtlearn.exception.PersonaNotFoundException;
import it.nextre.academy.nxtlearn.model.Persona;
import it.nextre.academy.nxtlearn.myutils.DummyData;
import it.nextre.academy.nxtlearn.service.PersonaService;
import it.nextre.academy.nxtlearn.service.impl.PersonaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/persona")
public class PersonaController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonaService personaService;


    // @RequestMapping(path="/new",method = RequestMethod.GET)
    @GetMapping("/new")
    public String getNewPersona(Model model) {
        System.out.println("LOG getNewPersona()");
        model.addAttribute("persona", personaService.getRandom());
        return "persona.html";
    }

    //@GetMapping("/")
    @GetMapping
    public String getPersone(Model model) {
        logger.info("LOG getPersone()");
        model.addAttribute("persone", personaService.getPersone());
        return "persone.html";
    }

    @GetMapping("/new/{qta}")
    public String getNewPersone(Model model, @PathVariable("qta") Integer qta) {
        System.out.println("LOG getNewPersone()");
        model.addAttribute("persone", personaService.getRandoms(qta));
        return "persone.html";
    }


    @GetMapping("/{id}")
    public String getPersona(Model model, @PathVariable("id") Integer id ) {
        logger.warn("LOG getPersona(), id: "+id);
        Persona tmp = personaService.getPersona(id);
        if (tmp!=null)
            model.addAttribute("persona", tmp);
        else
            //model.addAttribute("persona", new Persona());
            throw new PersonaNotFoundException();
        return "persona.html";
    }

}//end class
