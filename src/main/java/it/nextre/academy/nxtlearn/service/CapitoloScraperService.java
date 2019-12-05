package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Guida;

public interface CapitoloScraperService {
    Capitolo esecuzione(String url, Guida guida);
}//end class
