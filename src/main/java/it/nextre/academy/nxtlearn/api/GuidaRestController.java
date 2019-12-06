package it.nextre.academy.nxtlearn.api;

import it.nextre.academy.nxtlearn.dto.GuidaDto;
import it.nextre.academy.nxtlearn.dto.LezioneDto;
import it.nextre.academy.nxtlearn.exception.BadRequestException;
import it.nextre.academy.nxtlearn.exception.GuidaNotFoundException;
import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.model.Lezione;
import it.nextre.academy.nxtlearn.service.GuidaScraperService;
import it.nextre.academy.nxtlearn.service.GuidaService;
import it.nextre.academy.nxtlearn.service.LezioneService;
import it.nextre.academy.nxtlearn.service.LivelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guida")
public class GuidaRestController {

    @Autowired
    GuidaService guidaService;

    @Autowired
    LezioneService lezioneService;

    @Autowired
    LivelloService livelloService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Secured({"ROLE_SIMPLEUSER", "ROLE_ADMIN"})  // = jsr250 @RoleAllowed
    @GetMapping("/{id}")
    public GuidaDto getByID(@PathVariable("id") Integer id) {
        logger.info("LOG: getById, id=" + id);
        Guida tmp = guidaService.findById(id);
        if (tmp != null) {
            return guidaService.toDto(tmp, false);
        } else {
            throw new GuidaNotFoundException();
        }
    }

    @GetMapping("/latest")
    public List<GuidaDto> getLastTen() {
        logger.info("LOG: getLastTen" );
        List<Guida> guide = guidaService.getLastTen();
        List<GuidaDto> risp = new ArrayList<>();
        if (guide!=null && guide.size()>0){
            for(Guida guida : guide){
                risp.add(guidaService.toDto(guida, true));
            }
        }
        return risp;
    }

    @GetMapping
    public List<GuidaDto> getGuide() {
        logger.info("Log: getGuide()");

        List<GuidaDto> out = new ArrayList<>();
        guidaService.getAll().stream().forEach(g->{
            GuidaDto tmp = new GuidaDto();
            tmp.setId(g.getId());
            tmp.setDescrizione(g.getDescrizione());
            tmp.setImage(g.getImagePath());
            Map<String, Object> tmpLiv = new HashMap<>();
            tmpLiv.put("id",g.getLivello().getId());
            tmpLiv.put("descrizione",g.getLivello().getDescrizione());
            tmpLiv.put("difficolta",g.getLivello().getDifficolta());
            tmp.setLivello(tmpLiv);
            tmp.setNome(g.getNome());
            out.add(tmp);
        });

        return out ;
    }

    @Secured({"ROLE_ADMIN"})
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

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public GuidaDto addOne(@RequestBody @Valid GuidaDto tmp, BindingResult validator) {
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
        Guida g = new Guida();
        if (tmp != null) {
            g.setId(tmp.getId());
            g.setNome(tmp.getNome());
            g.setDescrizione(tmp.getDescrizione());
            g.setLivello(livelloService.findById((Integer) tmp.getLivello().get("id")));
            g.setImagePath(tmp.getImage());
        }
        g = guidaService.save(g);
        // System.out.println(g);
        return guidaService.toDto(g, false);
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

    @GetMapping("/search/{nome}")
    public List<GuidaDto> getByName(@PathVariable("nome") String nome) {
        logger.info("LOG: getByName, nome=" + nome);
        List<Guida> guide = guidaService.findByNome(nome);
        List<GuidaDto> tmp = new ArrayList<>();
        if (guide != null) {
            for (Guida g : guide) {
                tmp.add(guidaService.toDto(g, true));
            }
            return tmp;
        } else {
            throw new GuidaNotFoundException();
        }
    }

    @Secured({"ROLE_SIMPLEUSER", "ROLE_ADMIN"})  // = jsr250 @RoleAllowed
    @GetMapping("/short/{id}")
    public GuidaDto getShortByID(@PathVariable("id") Integer id) {
        logger.info("LOG: getShortByID, id=" + id);
        Guida tmp = guidaService.findById(id);
        if (tmp != null) {
            return guidaService.toDto(tmp, true);
        } else {
            throw new GuidaNotFoundException();
        }
    }

    @Secured({"ROLE_SIMPLEUSER", "ROLE_ADMIN"})  // = jsr250 @RoleAllowed
    @GetMapping("/{idg}/capitolo/{idc}/lezione/{idl}")
    public LezioneDto getLezioneByID(@PathVariable("idl") Integer id) {
        logger.info("LOG: getLezioneByID, id=" + id);
        Lezione tmp = lezioneService.findById(id);
        System.out.println("LEZIONE TROVATA");
        if (tmp != null) {
            System.out.println("PROVO A FARE IL DTO");
            LezioneDto tmp1 = lezioneService.toDto(tmp);
            System.out.println("LEZIONE DTO CREATO: "+tmp1);
            return tmp1;
        } else {
            throw new GuidaNotFoundException();
        }
    }



    @Autowired
    GuidaScraperService guidaScraperService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/addbyurl")
    public ResponseEntity getGuidaByUrl(@RequestBody Map<String, String> body) {
        logger.info("LOG: getGuidaByUrl");
        String url = body.get("url");
        logger.debug("URL passato a guidaRestController: " + url);
        if (url!=null && url.contains("http") && url.contains("html.it/")) {
            //todo performare un controllo, se già presente fare l'update della guida
            guidaScraperService.esecuzione(url);
        }else{
            throw new BadRequestException("Url sorgente non valido");
        }
        return new MyResponse(HttpStatus.OK,"OK").getPage();
    }

}//end class