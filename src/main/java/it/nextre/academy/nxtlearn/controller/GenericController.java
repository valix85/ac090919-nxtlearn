package it.nextre.academy.nxtlearn.controller;

import org.springframework.security.access.annotation.Secured;
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
    public String getHome(){
        return "home.html";
    }

    @GetMapping("/login")
    public String doLoginPage(){
        return "login.html";
    }





}//end class
