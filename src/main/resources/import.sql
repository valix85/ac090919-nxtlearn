-- file utilizzato DA HIBERNATE, in caso di ddl-auto create o create-drop, per popolare con dati di esempio o di default la nostra base dati
-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-database-initialization

-- In addition, a file named import.sql in the root of the classpath is executed on startup if Hibernate creates the schema from scratch (that is, if the ddl-auto property is set to create or create-drop). This can be useful for demos and for testing if you are careful but is probably not something you want to be on the classpath in production. It is a Hibernate feature (and has nothing to do with Spring).


-- INSERT INTO persona VALUES (7,"Filippo","Sberla");
-- INSERT INTO persona VALUES (8,"Chiara","Monella");
-- INSERT INTO persona VALUES (9,"Francesca","Brundi");
-- INSERT INTO persona VALUES (10,"Sabrina","Bellotti");
-- INSERT INTO persona VALUES (11,"Marinella","Vongola");
-- INSERT INTO persona VALUES (12,"Pasquale","Branzino");

-- il next_val è intratabelle, pertanto metto un valore alto che non verrà raggiunto con l'import automatico
-- UPDATE hibernate_sequence SET next_val = 1000;