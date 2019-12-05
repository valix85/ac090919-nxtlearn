package it.nextre.academy.nxtlearn.service;

import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Lezione;

public interface LezioneScraperService {
    Lezione esecuzione(String url, Capitolo capitolo);
}
