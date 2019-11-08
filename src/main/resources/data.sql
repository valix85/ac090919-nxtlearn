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
