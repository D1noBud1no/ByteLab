package com.school.management.util;

import com.school.management.model.*;
import com.school.management.repository.*;
import com.school.management.model.Materiale.Tipo;
import com.school.management.model.Iscrizione.StatoIscrizione;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(2)
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StudenteRepository studenteRepository;
    private final DocenteRepository docenteRepository;
    private final AulaRepository aulaRepository;
    private final PercorsoRepository percorsoRepository;
    private final CorsoRepository corsoRepository;
    private final MaterialeRepository materialeRepository;
    private final EsameRepository esameRepository;
    private final IscrizioneRepository iscrizioneRepository;

    @Override
    public void run(String... args) {

        // STUDENTI
        Studente s1 = createOrGetStudente("Alessandro", "Neri", "alessandro.neri@example.com", LocalDate.of(2001, 4, 10));
        Studente s2 = createOrGetStudente("Martina", "Gialli", "martina.gialli@example.com", LocalDate.of(1998, 11, 5));
        Studente s3 = createOrGetStudente("Federico", "Blu", "federico.blu@example.com", LocalDate.of(2000, 2, 20));
        Studente s4 = createOrGetStudente("Sofia", "Viola", "sofia.viola@example.com", LocalDate.of(1999, 8, 15));
        Studente s5 = createOrGetStudente("Lorenzo", "Verdi", "lorenzo.verdi@example.com", LocalDate.of(2003, 6, 25));

        // DOCENTI
        Docente d1 = createOrGetDocente("Giulia", "Rossi", "giulia.rossi@example.com");
        Docente d2 = createOrGetDocente("Matteo", "Bianchi", "matteo.bianchi@example.com");
        Docente d3 = createOrGetDocente("Elena", "Grigi", "elena.grigi@example.com");

        // AULE
        Aula aula1 = createOrGetAula("Aula Magna", 50, 1);
        Aula aula2 = createOrGetAula("Laboratorio Informatica", 20, 0);
        Aula aula3 = createOrGetAula("Aula 302", 30, 3);

        // PERCORSI FORMATIVI
        Percorso p1 = createOrGetPercorso("Data Science", "Analisi dei dati e machine learning");
        Percorso p2 = createOrGetPercorso("Cloud Computing", "Fondamenti di cloud e infrastrutture");
        Percorso p3 = createOrGetPercorso("AI Basics", "Introduzione all'intelligenza artificiale");

        // CORSI
        Corso c1 = createOrGetCorso("Python per Data Science", LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), 60, d1, aula1, p1);
        Corso c2 = createOrGetCorso("AWS Cloud Essentials", LocalDate.now().minusDays(20), LocalDate.now().plusDays(10), 40, d2, aula2, p2);
        Corso c3 = createOrGetCorso("Introduzione a TensorFlow", LocalDate.now().minusDays(10), LocalDate.now().plusDays(20), 50, d3, aula3, p3);
        Corso c4 = createOrGetCorso("Docker e Kubernetes", LocalDate.now().minusDays(25), LocalDate.now().plusDays(5), 35, d3, aula2, p2);

        // MATERIALI DIDATTICI
        createOrGetMateriale("Manuale Python", Tipo.PDF, "Guida completa a Python", c1);
        createOrGetMateriale("Slide AWS", Tipo.SLIDE, "Materiale per AWS Cloud Essentials", c2);
        createOrGetMateriale("Esercizi TensorFlow", Tipo.PDF, "Esercizi pratici su TensorFlow", c3);
        createOrGetMateriale("Guida Docker", Tipo.LINK, "Documentazione ufficiale Docker", c4);

        // ESAMI
        createOrGetEsame("Esame Python", LocalDate.now().plusDays(7), c1);
        createOrGetEsame("Esame AWS", LocalDate.now().plusDays(15), c2);
        createOrGetEsame("Test TensorFlow", LocalDate.now().plusDays(10), c3);
        createOrGetEsame("Prova Docker", LocalDate.now().plusDays(12), c4);

        // ISCRIZIONI
        createOrGetIscrizione(s1, c1, LocalDate.now().minusDays(20), StatoIscrizione.ATTIVA, 30);
        createOrGetIscrizione(s2, c2, LocalDate.now().minusDays(25), StatoIscrizione.COMPLETATA, 28);
        createOrGetIscrizione(s3, c1, LocalDate.now().minusDays(15), StatoIscrizione.RITIRATA, 0);
        createOrGetIscrizione(s4, c3, LocalDate.now().minusDays(18), StatoIscrizione.ATTIVA, 27);
        createOrGetIscrizione(s5, c4, LocalDate.now().minusDays(8), StatoIscrizione.ATTIVA, 0);
        createOrGetIscrizione(s1, c3, LocalDate.now().minusDays(5), StatoIscrizione.COMPLETATA, 25);
    }

    private Studente createOrGetStudente(String nome, String cognome, String email, LocalDate dataNascita) {
        return studenteRepository.findByEmail(email).orElseGet(() ->
                studenteRepository.save(Studente.builder().nome(nome).cognome(cognome).email(email).dataNascita(dataNascita).build()));
    }

    private Docente createOrGetDocente(String nome, String cognome, String email) {
        return docenteRepository.findByEmail(email).orElseGet(() ->
                docenteRepository.save(Docente.builder().nome(nome).cognome(cognome).email(email).build()));
    }

    private Aula createOrGetAula(String nome, int capienza, int piano) {
        return aulaRepository.findByNome(nome).orElseGet(() ->
                aulaRepository.save(Aula.builder().nome(nome).capienza(capienza).piano(piano).build()));
    }

    private Percorso createOrGetPercorso(String nome, String descrizione) {
        return percorsoRepository.findByNome(nome).orElseGet(() ->
                percorsoRepository.save(Percorso.builder().nome(nome).descrizione(descrizione).build()));
    }

    private Corso createOrGetCorso(String nome, LocalDate dataInizio, LocalDate dataFine, int durataOre, Docente docente, Aula aula, Percorso percorso) {
        return corsoRepository.findByNome(nome).orElseGet(() ->
                corsoRepository.save(Corso.builder()
                        .nome(nome)
                        .dataInizio(dataInizio)
                        .dataFine(dataFine)
                        .durataOre(durataOre)
                        .docente(docente)
                        .aula(aula)
                        .percorso(percorso)
                        .build()));
    }

    private void createOrGetMateriale(String titolo, Tipo tipo, String descrizione, Corso corso) {
        materialeRepository.findByTitolo(titolo).orElseGet(() ->
                materialeRepository.save(Materiale.builder()
                        .titolo(titolo)
                        .tipo(tipo)
                        .descrizione(descrizione)
                        .corso(corso)
                        .build()));
    }

    private void createOrGetEsame(String nome, LocalDate data, Corso corso) {
        esameRepository.findByNome(nome).orElseGet(() ->
                esameRepository.save(Esame.builder()
                        .nome(nome)
                        .data(data)
                        .corso(corso)
                        .build()));
    }

    private void createOrGetIscrizione(Studente studente, Corso corso, LocalDate dataIscrizione, StatoIscrizione stato, int voto) {
        iscrizioneRepository.findByStudenteAndCorso(studente, corso).orElseGet(() ->
                iscrizioneRepository.save(Iscrizione.builder()
                        .dataIscrizione(dataIscrizione)
                        .stato(stato)
                        .voto(voto)
                        .studente(studente)
                        .corso(corso)
                        .build()));
    }
}