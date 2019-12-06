package it.nextre.academy.nxtlearn.myutils;

import it.nextre.academy.nxtlearn.service.LezioneService;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
public class ScrapingUtils {

    @Value("${datafile.directory}")
    private String fileDirectory;

    private File file;
    @Autowired
    LezioneService lezioneService;
    private Properties prop = new Properties();
    /**
     * Rimuove l'html non necessario estraendo le stringe pulite
     *
     * @param elem
     * @return
     */
    public static String removeHtml(Elements elem) {
        String str = elem.toString();
        return str.replaceAll("\\<.*?\\>", "").strip();
    }
    public String scaricaImmagine(String urlImmagine, File directory, Integer idLezione, Integer idGuida, Integer idCapitolo) throws IOException {
        String fileName = "";
        try {
            //todo fixare con properties dinamico
            prop.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
            URL url = new URL(urlImmagine);
            fileName = url.getFile().substring(url.getFile().lastIndexOf("/") + 1);
            //File htmlFolder = new File(prop.getProperty("file.directory"));
            //salvataggio su HDD
            file = new File(fileDirectory + File.separator + idGuida + File.separator + directory + File.separator + idCapitolo + File.separator + idLezione);

            file.mkdirs();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream in = new BufferedInputStream(new URL(urlImmagine).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile() + File.separator + fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (
                IOException e) {
            System.out.println("Nessun file trovato");
        }
        return file.getPath() + File.separator + fileName;
    }


    public String modificaContenuto(String contenuto, String imgOld, String imgNew) {

        System.out.println("1- "+contenuto);
        String urlLink = imgNew.substring(fileDirectory.length()+1);
        System.out.println("2- "+urlLink);
        urlLink = urlLink.replaceAll("\\\\","/");
        System.out.println("3- "+urlLink);
        urlLink = "/data/"+urlLink;
        System.out.println("4- "+urlLink);



        contenuto = contenuto.substring(0, contenuto.indexOf(imgOld)) + urlLink + contenuto.substring(contenuto.indexOf(imgOld) + imgOld.length());



        return contenuto;
    }
}//end class