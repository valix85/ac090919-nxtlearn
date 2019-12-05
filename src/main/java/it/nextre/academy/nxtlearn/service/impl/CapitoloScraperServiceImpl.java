package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Guida;
import it.nextre.academy.nxtlearn.model.Lezione;
import it.nextre.academy.nxtlearn.myutils.ScrapingUtils;
import it.nextre.academy.nxtlearn.repository.CapitoloRepository;
import it.nextre.academy.nxtlearn.service.CapitoloScraperService;
import it.nextre.academy.nxtlearn.service.GuidaService;
import it.nextre.academy.nxtlearn.service.LezioneScraperService;
import it.nextre.academy.nxtlearn.service.LezioneService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class CapitoloScraperServiceImpl extends ScrapingUtils implements CapitoloScraperService {
    @Autowired
    private CapitoloRepository capitoloRepository;

    @Autowired
    private LezioneScraperService lezioneScraperService;

    //todo rimuovere variabili di classe

    private String nomeCapitolo;
    private List<String> nomeLezione = new ArrayList<>();
    private Integer i = 1;
    // private Guida guida;
    private Capitolo c;
    private List<Lezione> lezioni = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public Capitolo esecuzione(String url, Guida g) {
        try {
            final Document document = Jsoup.connect(url).get();
            for (Element divCapitoli : document.select("div.guide-index__chapter")) {
                nomeLezione = new ArrayList<>();
                for (Element testoCapitolo : divCapitoli.select("div.guide-index__chapter > span")) {
                    nomeCapitolo = testoCapitolo.ownText();
                    i++;
                    c = new Capitolo(nomeCapitolo, url, i, lezioni, g);
                    capitoloRepository.save(c);
                    logger.debug("Nome testoCapitolo: " + nomeCapitolo);
                }
                for (Element lezione : divCapitoli.select("a")) {
                    Lezione tmp = lezioneScraperService.esecuzione(lezione.attr("href"), c);
                }
                /*
                // estrae nomi dei capitoli e salva il capitolo
                for (Element capitolo : divCapitoli.select("span")) {
                    nome = capitolo.ownText();
                    // System.out.println(capitolo.ownText());
                    // lezioni.add(lezioneService.findById(1));
                    i++;
                    c = new Capitolo(
                            nome,
                            url,
                            i,
                            lezioni,
                            g
                    );
                    System.out.println("Capitolo: " + c);
                    capitoloRepository.save(c);
                    for (Element linkLezione : document.select("div.guide-index__chapter a")) {
                        String nomeLezione = removeHtml(linkLezione.getElementsByTag("h3")).substring(removeHtml(linkLezione.getElementsByTag("h3")).indexOf(" ") + 1);
                            System.out.println("nome lezione: " + nomeLezione + "\nnome capitolo: " + c.getNome());
                            System.out.println("LINK LEZIONE: " + linkLezione.attr("href"));
                            Lezione l = lezioneScraperService.esecuzione(linkLezione.attr("href"), c);
                            lezioni.add(l);
                    }
                    c.setLezioni(lezioni);
                }
                */
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }
}//end class