package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.model.*;
import it.nextre.academy.nxtlearn.myutils.ScrapingUtils;
import it.nextre.academy.nxtlearn.repository.GuidaRepository;
import it.nextre.academy.nxtlearn.service.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO Rivedere classe , aggiunta LOG e rimozioni variabili condivise

@Service
public class GuidaScraperServiceImpl extends ScrapingUtils implements GuidaScraperService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GuidaRepository guidaRepository;
    @Autowired
    private LivelloService livelloService;
    @Autowired
    private GuidaService guidaService;
    @Autowired
    private CapitoloScraperService capitoloScraperService;
    @Autowired
    private LezioneScraperService lezioneScraperService;
    @Autowired
    private CapitoloService capitoloService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private LezioneService lezioneService;
    private Guida g;
    private Allegato allegato;
    private Capitolo capitolo;
    private Lezione lezione;
    private PersonaGuida personaGuida;
    private String titolo = "";
    private String descrizione = "";
    private String img = "";
    private String difficolta = "";
    private List<Capitolo> capitoli = new ArrayList<>(1);
    private List<Lezione> lezioni = new ArrayList<>(1);
    private List<Allegato> allegati = new ArrayList<>(1);

    public void esecuzione(String url) {
        try {
            final Document document = Jsoup.connect(url).get();
            titolo = removeHtml(document.select("div.post__title"));
            descrizione = removeHtml((document.select("div.description")));
            img = removeHtml((document.select("div.guide-index--section > img")));
            difficolta = removeHtml((document.select("div.level-icon > label")));
            g = new Guida(
                    titolo,
                    url,
                    descrizione,
                    img,
                    livelloService.findByDescrizione(difficolta),
                    new ArrayList<>(),
                    capitoli
            );
            g = guidaService.newGuida(g);
            capitoloScraperService.esecuzione(url, g);
            /*
            capitoli.add(capitoloScraperService.esecuzione(url, g));
            g.setCapitoli(capitoli);
            for (Element elem : document.select("div.guide-index__chapter a")) {
                System.out.println(lezioneService.findLezioneByUrl(elem.attr("href")));
                lezioni.add(lezioneService.findLezioneByUrl(elem.attr("href")));
            }
            */
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        System.out.println(
                "\n".repeat(10) +
                        "Nome guida: " + g.getNome() + "\n" +
                        "Descrizione: " + g.getDescrizione() + "\n" +
                        "Url immagine: " + g.getImagePath() + "\n" +
                        "Url guida: " + g.getUrl() + "\n" +
                        "Livello: " + g.getLivello() + "\n" +
                        "\n".repeat(10)
        );
    }



}//end class
