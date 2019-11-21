-- file utilizzato per popolare con dati di esempio o di default la nostra base dati
-- https://www.baeldung.com/spring-boot-data-sql-and-schema-sql
insert into ruolo (id, data_creazione, data_modifica, nome) values
(1,20191115,null,"ROLE_ADMIN"),
(2,20191115,null,"ROLE_MODERATOR"),
(3,20191115,null,"ROLE_PUBLISHER"),
(4,20191115,null,"ROLE_SIMPLEUSER");


INSERT INTO livello (id, data_creazione, descrizione, difficolta) VALUES (1,now(),"facile", 1);
INSERT INTO livello (id, data_creazione, descrizione, difficolta) VALUES (2,now(),"medio", 2);
INSERT INTO livello (id, data_creazione, descrizione, difficolta) VALUES (3,now(),"difficile", 3);


INSERT INTO persona(id,nome,cognome)
VALUES
(1,"Valerio","Radice"),
(2,"Michele","Bossi"),
(3,"Michela","Manelli"),
(4,"Gino","Brunetta"),
(5,"Maria","Vongola"),
(6,"Gina","Branzino"),
(7,"Valerio","Radice");
-- pwd demo
INSERT INTO utenza (id, email, password) values
(1,"admin@demo.it","$2a$10$UlgcOBJZMOJSL8upaz79f.2c6Bj8Dr8Bt0QgqbkOuSBkh7UYh1pYu"),
(2,"moderatore@demo.it","$2a$10$UlgcOBJZMOJSL8upaz79f.2c6Bj8Dr8Bt0QgqbkOuSBkh7UYh1pYu"),
(3,"user1@demo.it","$2a$10$UlgcOBJZMOJSL8upaz79f.2c6Bj8Dr8Bt0QgqbkOuSBkh7UYh1pYu"),
(4,"user2@demo.it","$2a$10$UlgcOBJZMOJSL8upaz79f.2c6Bj8Dr8Bt0QgqbkOuSBkh7UYh1pYu"),
(5,"user3@demo.it","$2a$10$UlgcOBJZMOJSL8upaz79f.2c6Bj8Dr8Bt0QgqbkOuSBkh7UYh1pYu"),
(6,"publisher@demo.it","$2a$10$UlgcOBJZMOJSL8upaz79f.2c6Bj8Dr8Bt0QgqbkOuSBkh7UYh1pYu"),
(7,"valix85@gmail.com","$2a$10$Z.NiEj0CdrCntqFDd84JGuahYIC0jW11ebdVRSoC9zSxGluFt0WDm");
insert into rel_utenza_ruolo(ruolo_id,utenza_id) values
(1,1),
(2,2),
(4,3),
(4,4),
(4,5),
(4,7),
(3,6);
-- il next_val è intratabelle, pertanto metto un valore alto che non verrà raggiunto con l'import automatico
-- UPDATE hibernate_sequence SET next_val = 1000;
insert into guida( id, data_creazione, nome, url, descrizione, image_path, livello_id) values (1, now() ,"Guida Python", "https://www.html.it/guide/guida-python/","Python è uno dei linguaggi di programmazione più usati al mondo. Grazie alla sua sintassi asciutta e potente, ed al supporto multipiattaforma, è utilizzato per moltissime tipologie di applicazioni, dal networking, al web, fino al machine learning.","https://www.html.it/app/themes/html-2018/dist/images/logo-open.png",3);
insert into guida(id, data_creazione, nome, url, descrizione, image_path, livello_id) values(2, now(),"Guida Scratch", "https://www.html.it/guide/scratch-guida-alla-creazione-di-progetti-interattivi/", "Scratch è un ambiente di programmazione gratuito, con un linguaggio di programmazione grafico progettato per l’insegnamento della programmazione tramite primitive visive. È adatto ad utenti con competenze informatiche basilari, e si presta a molti progetti, da semplici esperimenti, ad applicazioni interattive e giochi. Questa guida descrive le basi per utilizzare Scratch partendo da zero, imparandone tutte le principali funzionalità.", "https://covers.oreillystatic.com/images/9780596517724/lrg.jpg",1);
insert into guida(id, data_creazione,nome,url,descrizione,image_path, livello_id)  values (3,now(),"Guida JavaScript","https://www.html.it/guide/guida-javascript-di-base/","JavaScript è il principale linguaggio di programmazione per lo sviluppo di web applications. Sempre più diffuso, tocca ormai gli ambiti mobile, server e desktop. Questa guida fornisce una panoramica completa e dettagliata per imparare ad utilizzarlo al meglio, partendo dalle caratteristiche fondamentali di JavaScript, fino ad arrivare ai dettagli più complessi ed avanzati.","https://www.html.it/app/themes/html-2018/dist/images/logo-open.png",1);
insert into guida(id, data_creazione,nome,url,descrizione,image_path, livello_id)  values (4,now(),"Guida Android","https://www.html.it/guide/guida-android/","Android è il sistema operativo mobile più diffuso attualmente sul mercato. Imparare a sviluppare app per Android è quindi la chiave d’accesso ad uno dei mercati più attivi nello sviluppo mobile. Questa guida completa e dettagliata fornisce tutte le informazioni necessarie per imparare a progettare ed implementare app moderne e funzionali per Android.","https://www.html.it/app/themes/html-2018/dist/images/logo-open.png",3);
insert into guida(id, data_creazione,nome,url,descrizione,image_path, livello_id)  values (5,now(),"Guida Swift","https://www.html.it/guide/guida-swift/","Swift è un linguaggio di programmazione realizzato da Apple per lo sviluppo di app mobile per il sistema operativo iOS. Questa guida parte dalle basi ed affronta tutti i principali argomenti per imparare a programmare con il linguaggio Swift, arrivando a comprenderne le principali funzionalità per realizzare app professionali per dispositivi come iPhone ed iPad.","https://www.html.it/app/themes/html-2018/dist/images/logo-open.png",2);
insert into rel_persona_guida (persona_id,guida_id) values
(1,1),
(1,3),
(5,2),
(4,3),
(3,1),
(3,2);
insert into rel_persona_guida (persona_id,guida_id) values (1,3);

insert into capitolo(id, data_creazione, nome, ordine_capitolo,guida_id) values
(1, 20191115, "Primi passi con il linguaggio", 1,1),
(2, 20191115, "Variabile e tipi di dati", 2,1),
(3, 20191115, "La programmazione con Python", 3,1),
(4, 20191115, "La programmazione ad oggetti con Python", 4,1),
(5, 20191115, "GUI con Python", 5,1),
(6, 20191115, "Approfondimenti", 6,1), -- Fine capitoli guida Python
(7, 20191115, "Le basi di Scratch",1,2),
(8, 20191115, "Creiamo un videogioco da zero",2,2),
(9, 20191115, "Creiamo un filmato interattivo",3,2), -- Fine capitoli guida Scratch
(10, 20191115, "Primi passi con JavaScript", 1,3 ),
(11, 20191115, "Gli elementi base del linguaggio",  2,3 ),
(12, 20191115, "Controllo di flusso",  3 ,3),  -- Fine capitoli guida JavaScript
(13, 20191115, "Primi passi con Android", 1,4),
(14, 20191115, "Il cuore di un’app Android", 2,4),
(15, 20191115, "L’interfaccia grafica (GUI)", 3,4),
(16, 20191115, "Lo storage", 4,4),  -- Fine capitoli guida Android
(17, 20191115, "Introduzione",1,5),
(18, 20191115, "Le basi del linguaggio",2,5),
(19, 20191115, "Concetti avanzati",3,5); -- Fine capitoli guida Swift

insert into lezione(id,data_creazione,autore,titolo, contenuto,ordine_lezione,capitolo_id) values
(1,20161102,"Ezio Melotti","Introduzione e un po’ di storia","Python è un linguaggio di programmazione moderno, dalla sintassi semplice e potente che ne facilita l’apprendimento. Gli ambiti di applicazione di questo linguaggio di programmazione sono svariati:",1,1), -- Inizio lezioni Python
(2,20191107,"Ezio Melotti","Perché usare Python","Oggi esistono numerosi linguaggi di programmazione, ma cosa spinge ad usare Python, cosa c’è di particolare in questo linguaggio? Per rendercene conto, in questa lezione esaminiamo alcuni dei punti di forza di Python ",2,1),
(3,20191130,"Ezio Melotti","Installare Python su Windows, Linux e Mac","In questa guida utilizzeremo Python 3. È possibile scaricare liberamente l’ultima versione di Python 3 (3.5 al momento della scrittura) dal sito ufficiale.",3,1), -- Fine capitolo 1
(4,20170220,"Ezio Melotti","Variabili e commenti in Python","In linguaggi come C, le variabili fanno riferimento a specifiche locazioni di memoria che hanno una dimensione fissa che dipende dal loro tipo (per questo è necessario specificare il tipo quando si dichiara una variabile).",1,2),
(5,20170306,"Ezio Melotti","Numeri e operatori logici in Python","Come abbiamo visto nella lezione precedente, in Python ci sono 4 tipi di dati numerici: interi (int), razionali (float), complessi (complex), e booleani (bool).",2,2),
(6,20170320,"Ezio Melotti","Stringhe in Python","In questa lezione ci occupiamo di come trattare le stringhe in Python. Abbiamo già visto che per dichiarare una stringa è sufficiente assengare ad una nuova variabile un testo",3,2), -- Fine capitolo 2
(7,20170703,"Ezio Melotti","Istruzioni condizionali","In questa lezione vedremo come implementare istruzioni condizionali in Python usando il costrutto if-elif-else",1,3),
(8,20170717,"Ezio Melotti","Cicli: for e while","In Python esistono due tipi di cicli (anche detti loop):il ciclo for: esegue un’iterazione per ogni elemento di un iterabile;il ciclo while: itera fintanto che una condizione è vera.",2,3),
(9,20170825,"Ezio Melotti","List/Set/Dict Comprehension","Le comprehension sono uno strumento che ci permette di creare in modo conciso e conveniente nuove liste, set, e dizionari partendo da una sequenza di valori esistenti.",3,3), -- Fine capitolo 3
(10,20171120,"Ezio Melotti","La programmazione ad oggetti","In uno dei capitoli introduttivi di questa guida abbiamo visto che Python è un linguaggio multi-paradigma, che supporta cioè sia la programmazione procedurale (che fa uso delle funzioni)",1,4),
(11,20171211,"Ezio Melotti","Classi in Python","Nella precedente lezione abbiamo introdotto i concetti principali della programmazione ad oggetti e abbiamo visto alcuni semplici esempi in Python.",2,4), -- Fine capitolo 4
(12, 20180423,"Ezio Melotti","GUI sui Python: i Framework da usare","In questa lezione vedremo una breve panoramica dei framework che consentono di costruire interfacce grafiche in Python.",1,5), --Fine capitolo 5
(13, 20060317,"Ezio Melotti","Python e Java","Se qualcuno fosse interessato all’utilizzo delle librerie di java all’interno di python, può utilizzare “Jython“.",1,6), -- Fine capitolo 6 -- Fine lezioni su Python

(14,20190911,"Vigamus Academy","Primi passi su Scratch","Scratch è un ambiente di programmazione gratuito, con un linguaggio di programmazione di tipo grafico.",1,7), -- Inizio lezioni su Scratch
(15,20190911,"Vigamus Academy","Esploriamo i Costumi di Sprite e Stage","Tra tutti gli strumenti forniti da Scratch, quello dedicato ai colori di spnascondono anche degli altri strumenti che, assieme al nostro.",2,7),
(16,20190911,"Vigamus Academy","Controlliamo le azioni del nostro personaggio","Guardano le azioni visibili tra i blocchi di Scratch, stesto. In realtà, Scratch permette di fare molto altro, come dimostra una lista che si chi.",1,8),
(17,20190911,"Vigamus Academy","Animiamo un personaggio che parla","Avendo visto solo le basi di ScratchScratch. In questa lezione ci concentreremo quindi su un solo personaggio, il",1,9), -- Fine guida Scratch
(18, 20140228, "Andrea Chiarelli", "Introduzione a JavaScript", "JavaScript è uno dei linguaggi di programmazione più usati al mondo, anche se con una storia di alti e bassi, la sua consolidata presenza nell’olimpo dei grandi linguaggi come C, C++ e Java è certa.",1,10), -- Inizio guida JavaScript
(19,20140228, "Andrea Chiarelli","Gli strumenti di lavoro" ,"Come per qualsiasi linguaggio di programmazione, per iniziare a lavorare con JavaScript c’è bisogno di almeno tre strumenti: un editor, un interprete o compilatore, un debugger.",2,10),
(20,20150925, "Andrea Chiarelli","ECMAScript 6 e il supporto dei browser" ,"Abbiamo accennato al fatto che l’ultima versione delle specifiche di JavaScript è stata pubblicata a giugno 2015 ed è nota come ECMAScript 2015 o ECMAScript 6, abbreviata spesso con ES6.",3,10),
(21,20140204, "Andrea Chiarelli","Commenti, punti e virgola e maiuscole" ,"Dopo aver tratteggiato i caratteri generali di JavaScript, ripassato un po’ di storia, fatto il punto sulle attuali prospettive, visti gli strumenti di sviluppo, possiamo iniziare ad esaminare",1,11),
(22,20140204, "Andrea Chiarelli","Stringhe, numeri e altri tipi di dati JavaScript" ,"JavaScript prevede cinque tipi di dato primitivi, numeri, stringhe, booleani, null e undefined, e un un tipo di dato complesso, gli oggetti.",2,11),
(23,20140422, "Andrea Chiarelli","Variabili, costanti e dichiarazioni" ,"Come per ogni linguaggio, anche con JavaScript possiamo utilizzare le variabili per memorizzare valori o oggetti durante l’esecuzione degli script. ",3,11),
(24,20140527, "Andrea Chiarelli","If, istruzioni condizionali e blocchi di codice" ,"Come è noto, un programma (o uno ‘script’) è una sequenza di istruzioni eseguite dall’engine JavaScript. Le istruzioni altro non sono che un flusso di comandi che stabiliscono il comportamento",1,12), -- Fine guida JavaScript
(25,20190911,"Giuseppe Maggi", "Introduzione: perchè Android","Android è il principale sistema operativo per il mobile al mondo. Stando alle statistiche, anima circa tre dispositivi su quattro lasciando lo spazio restante ad iOS di Apple." ,1,13), -- Inizio guida Android
(26,20151221, "Giuseppe Maggi","L'SDK e l'ambiente di sviluppo" ,"Il primo passo per iniziare a sviluppare app Android consiste nel dotarsi degli strumenti adeguati. Il cuore di questo tipo di programmazione risiede in un ambiente noto come Android SDK.",2,13),
(27,20130221, "Giuseppe Maggi","Activity, la prima pagina dell'applicazione" ,"Il primo passo per iniziare a sviluppare app Android consiste nel dotarsi degli strumenti adeguati. Il cuore di questo tipo di programmazione risiede in un ambiente noto come Android SDK.",1,14),
(28,20150221, "Giuseppe Maggi","Il ciclo di vita di un'Activity" ,"Il progetto approntato nel capitolo precedente con l’aiuto dell’IDE può essere ora analizzato nel dettaglio. Lo scopo che ci prefiggiamo è quello di osservare da vicino come è fatta un’Activity.",2,14),
(29,20131203, "Giuseppe Maggi","Gestire le risorse e gli asset" ,"Nelle applicazioni Android il codice Java richiama spesso degli elementi interni al progetto come file XML, stringhe, numeri, immagini ed altro ancora.",3,14),
(30,20151113, "Giuseppe Maggi","Intent e i messaggi" ,"Il progetto approntato nel capitolo precedente con l’aiuto dell’IDE può essere ora analizzato nel dettaglio. Lo scopo che ci prefiggiamo è quello di osservare da vicino come è fatta un’Activity.",4,14),
(31,20151203, "Giuseppe Maggi","Le permission" ,"Un’applicazione Android vive in all’interno di una sandbox, ovvero un ambiente chiuso in cui l’applicazione opera in maniera sostanzialmente isolata dal resto del sistema.",5,14),
(32,20140403, "Giuseppe Maggi","Layout di una pagina Android" ,"Un’Activity ha bisogno di un volto, di un suo aspetto grafico. Sempre. Anche nei casi più semplici, come quando si limita a stampare la stringa “Hello World!”.",1,15), -- Fine guida Android
(33,20191115,"Antonio Calanducci","Introduzione al linguaggio Swift","Durante la WorldWide Developer Conference (WWDC) del 2 giugno 2014, Apple ha presentato un nuovo linguaggio di programmazione.",1,17), -- Inizio guida Swift
(34,20190911,"Antonio Calanducci","Variabili, costanti e tipi di dati fondamentali","Si noti che non è necessario utilizzare il punto e virgola al termine delle istruzioni in Swift, e questa è in generale una buona norma suggerita da Apple stessa, per rendere il codice più leggibile",1,18),
(35,20190911,"Antonio Calanducci","Il tipo optional","Una delle caratteristiche di Swift è il fatto di essere un linguaggio sicuro per design, fornendo opportuni meccanismi per evitare l'esecuzione di codice non corretto, ed il verificarsi di effetti collaterali",2,18),
(36,20190911,"Antonio Calanducci","Ereditarietà","Dopo avere introdotto i concetti di base della programmazione orientata agli oggetti, come la definizione di una classe e la creazione di istanze, vediamo come possiamo implementare il meccanismo di ereditarietà delle classi in Swift.",1,19); -- Fine guida Swift


insert into allegato(id, data_creazione, data_modifica, descrizione_allegato, nome_file_nostro, nome_file_origine, ordine_allegato, lezione_id) values
(1,20190911,null,"Scratch","nomeFN","scratch.zip",1,1),
(2,20190911,null,"descrizione","nomeFN","nomeFO",2,2),
(3,20190911,null,"descrizione","nomeFN","nomeFO",3,3),
(4,20190911,null,"descrizione","nomeFN","nomeFO",4,4),
(5,20190911,null,"descrizione","nomeFN","nomeFO",5,5),
(6,20190911,null,"descrizione","nomeFN","nomeFO",6,6);