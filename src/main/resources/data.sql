-- file utilizzato per popolare con dati di esempio o di default la nostra base dati
-- https://www.baeldung.com/spring-boot-data-sql-and-schema-sql
INSERT INTO persona(id,nome,cognome)
VALUES
(1,"Mario","Red"),
(2,"Michele","Bossi"),
(3,"Michela","Manelli"),
(4,"Gino","Brunetta"),
(5,"Maria","Vongola"),
(6,"Gina","Branzino");
-- il next_val è intratabelle, pertanto metto un valore alto che non verrà raggiunto con l'import automatico
-- UPDATE hibernate_sequence SET next_val = 1000;
INSERT INTO livello (id, data_creazione, descrizione, difficolta) VALUES (1,now(),"facile", 1);
INSERT INTO livello (id, data_creazione, descrizione, difficolta) VALUES (2,now(),"medio", 2);
INSERT INTO livello (id, data_creazione, descrizione, difficolta) VALUES (3,now(),"difficile", 3);
INSERT INTO guida( id, data_creazione, nome, url, descrizione, image_path, livello_id) VALUES (1, now() ,"Introduzione: perché Android", "https://www.html.it/pag/19496/introduzione-perch-android/","Android è il sistema operativo mobile più diffuso attualmente sul mercato. Imparare a sviluppare app per Android è quindi la chiave d’accesso ad uno dei mercati più attivi nello sviluppo mobile. Questa guida completa e dettagliata fornisce tutte le informazioni necessarie per imparare a progettare ed implementare app moderne e funzionali per Android.","https://www.html.it/app/themes/html-2018/dist/images/logo-open.png",3);
INSERT INTO guida(id, data_creazione, nome, url, descrizione, image_path, livello_id) VALUES(2, now(),"Scratch, guida alla creazione di progetti interattivi", "https://www.html.it/guide/scratch-guida-alla-creazione-di-progetti-interattivi/", "Scratch è un ambiente di programmazione gratuito, con un linguaggio di programmazione grafico progettato per l’insegnamento della programmazione tramite primitive visive. È adatto ad utenti con competenze informatiche basilari, e si presta a molti progetti, da semplici esperimenti, ad applicazioni interattive e giochi. Questa guida descrive le basi per utilizzare Scratch partendo da zero, imparandone tutte le principali funzionalità.", "https://covers.oreillystatic.com/images/9780596517724/lrg.jpg",1);
INSERT INTO guida(id, data_creazione,nome,url,descrizione,image_path, livello_id)  VALUES (3,now(),"Guida JavaScript","https://www.html.it/guide/guida-javascript-di-base/","JavaScript è il principale linguaggio di programmazione per lo sviluppo di web applications. Sempre più diffuso, tocca ormai gli ambiti mobile, server e desktop. Questa guida fornisce una panoramica completa e dettagliata per imparare ad utilizzarlo al meglio, partendo dalle caratteristiche fondamentali di JavaScript, fino ad arrivare ai dettagli più complessi ed avanzati.","https://www.html.it/app/themes/html-2018/dist/images/logo-open.png",1);
INSERT INTO guida(id, data_creazione,nome,url,descrizione,image_path, livello_id)  VALUES (4,now(),"Guida Android","https://www.html.it/guide/guida-android/","Android è il sistema operativo mobile più diffuso attualmente sul mercato. Imparare a sviluppare app per Android è quindi la chiave d’accesso ad uno dei mercati più attivi nello sviluppo mobile. Questa guida completa e dettagliata fornisce tutte le informazioni necessarie per imparare a progettare ed implementare app moderne e funzionali per Android.","https://www.html.it/app/themes/html-2018/dist/images/logo-open.png",3);
INSERT INTO guida(id, data_creazione,nome,url,descrizione,image_path, livello_id)  VALUES (5,now(),"Guida Swift","https://www.html.it/guide/guida-swift/","Swift è un linguaggio di programmazione realizzato da Apple per lo sviluppo di app mobile per il sistema operativo iOS. Questa guida parte dalle basi ed affronta tutti i principali argomenti per imparare a programmare con il linguaggio Swift, arrivando a comprenderne le principali funzionalità per realizzare app professionali per dispositivi come iPhone ed iPad.","https://www.html.it/app/themes/html-2018/dist/images/logo-open.png",2);
insert into rel_persona_guida (persona_id,guida_id) values
(1,1),
(1,3),
(5,2),
(4,3),
(3,1),
(3,2);

insert into rel_persona_guida (persona_id,guida_id) values (1,3);

insert into capitolo(id, data_creazione, nome, ordine_capitolo,guida_id) values
(1, 20191115, "Primi passi con JavaScript", 1,3 ),
(2, 20191115, "Gli elementi base del linguaggio",  2,3 ),
(3, 20191115, "Controllo di flusso",  3 ,3),
(4, 20191115, "Primi passi con Android", 1,4);
insert into lezione(id,data_creazione,autore,titolo, contenuto,ordine_lezione,capitolo_id) values
(1, 20140228, "Andrea Chiarelli", "Introduzione a JavaScript", "JavaScript è uno dei linguaggi di programmazione più usati al mondo, anche se con una storia di alti e bassi, la sua consolidata presenza nell’olimpo dei grandi linguaggi come C, C++ e Java è certa. ",1,1),
(2,20140228, "Andrea Chiarelli","Gli strumenti di lavoro" ,"Come per qualsiasi linguaggio di programmazione, per iniziare a lavorare con JavaScript c’è bisogno di almeno tre strumenti: un editor, un interprete o compilatore, un debugger.",2,1),
(3,20190911,"Giuseppe Maggi", "Introduzione: perchè Android","Android è il principale sistema operativo per il mobile al mondo. Stando alle statistiche, anima circa tre dispositivi su quattro lasciando lo spazio restante ad iOS di Apple." , 1, 4);

