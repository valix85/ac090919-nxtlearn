package it.nextre.academy.nxtlearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Controller
public class GenericController {

    @GetMapping({"/","/home","/index"})
    public String getHome(Model model){
        System.out.println("LOG: getHome()");
        model.addAttribute("dataora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/mm/YYYY EEEE HH:MM:ss")));

        model.addAttribute("dtobj", LocalDateTime.now());

        //estrazione del lotto
        List<Integer> numeri = Arrays.asList(15,7,4,8,25,60);
        model.addAttribute("estratti", numeri);

        return "homeV1.html";
    }

    @GetMapping("/login")
    public String doLoginPage(){
        return "login.html";
    }

}//end class
