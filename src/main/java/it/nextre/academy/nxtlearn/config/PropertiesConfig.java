package it.nextre.academy.nxtlearn.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PropertiesConfig {
    //Funziona ma implica di replicare tutte le volte il nome della proprietà
    //@Value("${giornale.formatter.data.pattern}")
    //private String dataPattern;

    //meglio una soluzione dinamica
    @Autowired
    private Environment env;
    public String getValue(String name){
        return env.getRequiredProperty(name);
    }
}//end class
