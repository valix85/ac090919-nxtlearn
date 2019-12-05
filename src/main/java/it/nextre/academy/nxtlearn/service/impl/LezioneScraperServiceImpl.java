package it.nextre.academy.nxtlearn.service.impl;

import it.nextre.academy.nxtlearn.model.Allegato;
import it.nextre.academy.nxtlearn.model.Capitolo;
import it.nextre.academy.nxtlearn.model.Lezione;
import it.nextre.academy.nxtlearn.myutils.ScrapingUtils;
import it.nextre.academy.nxtlearn.repository.AllegatoRepository;
import it.nextre.academy.nxtlearn.repository.CapitoloRepository;
import it.nextre.academy.nxtlearn.repository.LezioneRepository;
import it.nextre.academy.nxtlearn.service.CapitoloService;
import it.nextre.academy.nxtlearn.service.LezioneScraperService;
import it.nextre.academy.nxtlearn.service.impl.LezioneServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class LezioneScraperServiceImpl extends ScrapingUtils implements LezioneScraperService {
    @Autowired
    private LezioneRepository lezioneRepository;
    @Autowired
    private LezioneServiceImpl lezioneService;
    @Autowired
    private CapitoloRepository capitoloRepository;
    @Autowired
    private CapitoloService capitoloService;
    @Autowired
    private AllegatoRepository allegatoRepository;
    private static int i=0, j = 0;
    private Allegato allegato;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Lezione l;
    private String titolo, descrizione, autore, filename, posizioneImmagineLocale = "";
    private Elements contenuto = null;
    private List<Allegato> allegati = new ArrayList<>();
    public Lezione esecuzione(String url, Capitolo capitolo) {
        try {
            final Document document = Jsoup.connect(url).get();
            titolo = removeHtml((document.select("div.post__title")));
            descrizione = removeHtml((document.select("div.description")));
            autore = removeHtml((document.select("a.author__name")));
            contenuto = (document.select("div.fotogallery-js, .editorial, .editorial--no-related, .prism-js"));
            l = new Lezione(titolo, descrizione, autore, contenuto.html(), url, i++, capitolo, new ArrayList<>());
            String newContenuto = l.getContenuto();
            lezioneRepository.save(l);
            /**
             * Ciclo for-each per estrapolare immagini dalla guida
             */
            for (Element img : contenuto.select("img")) {
                // per ogni img la salvo su disco, ritorna indirizzo file locale e lo assegnamo ad una stringa
                File directory = new File("img");
                posizioneImmagineLocale = scaricaImmagine(img.attr("src"), directory, l.getId(), capitolo.getGuida().getId(), capitolo.getId());
                // metodo(contenuto, link immagine nel contenuto, ritorno metodo1), ritorna contenuto
                newContenuto = modificaContenuto(newContenuto, img.attr("src"), posizioneImmagineLocale);
                //setto contenuto
                l.setContenuto(newContenuto);
            }
            /**
             * Cilco for-each per estrapolare gli allegati dalla guida
             */
            for (Element attachment : document.select("div.cta-container.cta-container--alignRight > a.cta.cta--download")) {
                filename = attachment.attr("href").substring(attachment.attr("href").lastIndexOf("/") + 1);
                try {
                    Allegato tmp = allegato = new Allegato(filename, "nomeCustom", j++, "descrizione del file" + filename, l);
                    allegatoRepository.save(tmp);
                    File directory = new File("allegati");
                    scaricaImmagine("https://www.html.it" + attachment.attr("href"), directory, l.getId(), capitolo.getGuida().getId(), capitolo.getId());
                } catch (IllegalArgumentException e) {
                    System.out.println("LA GUIDA NON CONTIENE IMMAGINI");
                }
            }
            l.setAllegati(allegati);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return l;
    }
}