# testNicMa
Progetto per salvare elementi da una chiamata REST in un database

Questo progetto ha lo scopo di mostrare come salvare gli elementi restituiti da una chiamata REST in un database utilizzando Java, Spring Boot, Hibernate e MySQL.

## Prerequisiti:
Prima di iniziare, assicurati di avere installati i seguenti componenti:
- Java JDK 8 o versioni successive
- Maven
- MySQL
- Un client MySQL come MySQL Workbench o phpMyAdmin

## Configurazione del database:
Per configurare il database, segui questi passaggi:

- Crea un database MySQL e un utente con i permessi per accedervi

## Modifica le seguenti proprietà nel file application.properties:
```application.properties.xml
spring.datasource.url=jdbc:mysql://localhost:3306/testNicMa
spring.datasource.username=db_user
spring.datasource.password=db_password
```
## Assicurati che il driver MySQL sia presente nel file pom.xml nella directory principale del progetto:
```pom.xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

## Esecuzione dell'applicazione
Per eseguire l'applicazione, segui questi passaggi:

- Apri una finestra del terminale nella directory principale del progetto.
- Esegui il comando mvn spring-boot:run per compilare ed eseguire l'applicazione.
- L'applicazione sarà in esecuzione all'indirizzo http://localhost:8080.

## Utilizzo dell'applicazione
L'applicazione ha sei endpoint:

- GET /posts restituisce tutti i post salvati nel database in formato JSON.
- GET /import esegue una chiamata REST all'indirizzo https://jsonplaceholder.typicode.com/posts per recuperare una lista di post e li salva nel database.
- GET /new inserimento nuovo elemento da form 
- POST /new crea un nuovo elemento post e lo salva nel database
- POST /saveCustomJson inserimento elemento nel database da una stringa (json)
- GET /importCustomJsonFile inserimento elemento nel database da un file .json 

Per testare l'applicazione, puoi utilizzare uno strumento come Postman per effettuare una chiamata GET e POST a http://localhost:8080/. Dovresti ricevere una risposte con lo status code 200.

