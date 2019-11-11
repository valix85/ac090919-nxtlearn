package it.nextre.academy.nxtlearn.api;

import it.nextre.academy.nxtlearn.exception.LivelloNotFoundException;
import it.nextre.academy.nxtlearn.model.Livello;
import it.nextre.academy.nxtlearn.service.LivelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livello")
public class LivelloController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LivelloService livelloService;

    @GetMapping({"/findById/{id}", "/{id}", "/getById/{id}", "/get/{id}"})
    public Livello getByID(@PathVariable("id") Integer id) {
        logger.info("LOG: getByIdLivello, id=" + id);
        Livello tmp = livelloService.findById(id);
        if (tmp != null) {
            return tmp;
        } else {
            throw new LivelloNotFoundException();
        }
    }

    @GetMapping({"/", "/all", ""})
    public List<Livello> getLivelli() {
        logger.info("Log: getLivelli()");
        return livelloService.getAll();
    }
}//end class
