package it.nextre.academy.nxtlearn.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/guida")
public class GuidaController {


    @Secured({"ROLE_ADMIN"})
    @GetMapping("/nuova-guida")
    public String doNuovaGuidaPage(){
        return "nuova-guida.html";
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/add")
    public String doAdd(@RequestParam("url") String url){
        System.out.println(url);
        //todo fare scraping e salvataggio
        return "home";
    }

}//end class
