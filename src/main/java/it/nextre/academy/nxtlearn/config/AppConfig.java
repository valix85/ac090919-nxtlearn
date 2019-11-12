package it.nextre.academy.nxtlearn.config;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // sconsigliato perchè il logger level è quello di spring in quanto creato nel contesto di spring
    // https://lankydan.dev/2019/01/09/configuring-logback-with-spring-boot
    // https://i.stack.imgur.com/7o9Kk.png
    @Bean
    public Logger getLogger(){
        return LoggerFactory.getLogger(this.getClass());
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}//end class
