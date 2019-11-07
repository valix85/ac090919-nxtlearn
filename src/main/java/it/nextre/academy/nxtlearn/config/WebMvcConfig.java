package it.nextre.academy.nxtlearn.config;


import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// @EnableWebMvc //se attivato resetta la pre-configurazione di springboot e sarà necessario specificare nuovamente tutte le configurazioni del viewResolver e altro...
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //override dei metodi per impostare tutte le possibili configurazioni di spring WebMvc
}//end class
