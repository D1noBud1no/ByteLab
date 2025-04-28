# 🎓 Private School Backend

Sistema backend per la gestione di una scuola privata  
Robusto, sicuro ed estensibile — sviluppato con Java 23 e Spring Boot.

---

## 📌 Obiettivo

Il progetto "Private School Backend" è stato progettato per gestire corsi, studenti, docenti, ruoli e altre funzionalità correlate a un sistema scolastico privato.  
L’obiettivo è fornire un’API solida e sicura per la gestione centralizzata delle informazioni scolastiche.

---

## ⚙️ Tecnologie utilizzate

- **Linguaggio**: Java 23  
- **Framework principale**: Spring Boot  
- **Database**: MySQL  
- **Librerie**:
  - Spring Security (autenticazione/autorizzazione)
  - Spring Data JPA (interazione con il database)
  - iText (generazione PDF)
  - SpringDoc OpenAPI (documentazione API)

---

## 🧱 Architettura del progetto

Il progetto adotta un'architettura a livelli, seguendo i principi della Clean Architecture:
Controller → Service → Repository → Model ↑ DTO

### Descrizione livelli:
- **Controller**: gestisce le richieste HTTP e invoca i servizi.
- **Service**: contiene la logica di business.
- **Repository**: accede al database tramite Spring Data JPA.
- **Model**: rappresenta le entità del dominio.
- **DTO (Data Transfer Object)**: oggetti per il passaggio dei dati tra livelli.

---

## 🔐 Sicurezza e autenticazione

- **Autenticazione**: JWT (JSON Web Token) tramite Spring Security.  
- **Autorizzazione**: basata sui ruoli:
  - `ROLE_ADMIN`
  - `ROLE_TEACHER`
  - `ROLE_USER`
- **Crittografia**: password sicure con algoritmo BCrypt.

---

## ✅ Funzionalità principali

- Gestione di utenti, ruoli, corsi e aule.
- Iscrizione degli studenti ai corsi.
- Generazione di PDF per i profili degli studenti.
- Endpoint sicuri con JWT e controllo dei ruoli.
- Documentazione interattiva tramite Swagger UI.

---

## ➕ Funzionalità aggiuntive

- Gestione di materiali didattici associati ai corsi.
- Esami e votazioni per gli studenti.
- Mocking con Mockito per test unitari.

---

## 🧪 Testing

- **Unit Test**: con JUnit.  
- **Mocking**: con Mockito.  
- **Integration Test**: tramite Postman.

---

## 🚀 Avvio del progetto

1. **Clona il repository**:
   ```bash
   git clone https://github.com/tuo-repository/private-school-backend.git

2. **Configura il database**:
Modifica il file application.properties con i dettagli del tuo database MySQL.

3. **Avvia l’applicazione**:
   mvn spring-boot:run

4. **Accedi alla documentazione API**:<vscode_annotation details='%5B%7B%22title%22%3A%22hardcoded-credentials%22%2C%22description%22%3A%22Embedding%20credentials%20in%20source%20code%20risks%20unauthorized%20access%22%7D%5D'>:</vscode_annotation>
Swagger UI

📦 Dataset iniziale
Incluso tramite la classe DataLoader:

Ruoli: ROLE_ADMIN, ROLE_TEACHER, ROLE_USER.
Utenti di esempio: amministratori, docenti e studenti.
Corsi: con materiali didattici e iscrizioni simulate.
🧰 Design Pattern utilizzati
Singleton: configurazioni centralizzate dei componenti.
DTO: trasferimento dati tra livelli.
Repository Pattern: accesso ai dati tramite Spring Data.
Factory: creazione di oggetti complessi (es. mapper).


