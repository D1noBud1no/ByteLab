package com.school.management.auth.util;

import com.school.management.auth.authmodel.AuthRuolo;
import com.school.management.auth.authmodel.AuthUtente;
import com.school.management.auth.authmodel.Ruolo;
import com.school.management.auth.authrepository.AuthRuoloRepository;
import com.school.management.auth.authrepository.AuthUtenteRepository;
import com.school.management.model.Docente;
import com.school.management.model.Studente;
import com.school.management.repository.DocenteRepository;
import com.school.management.repository.StudenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Order(1)
@RequiredArgsConstructor
public class DataLoaderAuth implements CommandLineRunner {

    private final AuthRuoloRepository ruoloRepository;
    private final AuthUtenteRepository utenteRepository;
    private final StudenteRepository studenteRepository;
    private final DocenteRepository docenteRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // Crea i ruoli se mancanti
        for (Ruolo ruolo : Ruolo.values()) {
            ruoloRepository.findByNome(ruolo).orElseGet(() ->
                    ruoloRepository.save(AuthRuolo.builder().nome(ruolo).build()));
        }

        // Studenti
        assegnaUtenteAStudente("alessandro.neri@example.com", "alessandro", "alessandro123");
        assegnaUtenteAStudente("martina.gialli@example.com", "martina", "martina123");
        assegnaUtenteAStudente("federico.blu@example.com", "federico", "federico123");
        assegnaUtenteAStudente("sofia.viola@example.com", "sofia", "sofia123");
        assegnaUtenteAStudente("lorenzo.verdi@example.com", "lorenzo", "lorenzo123");

        // Docenti
        assegnaUtenteADocente("giulia.rossi@example.com", "giulia", "giulia123");
        assegnaUtenteADocente("matteo.bianchi@example.com", "matteo", "matteo123");
        assegnaUtenteADocente("elena.grigi@example.com", "elena", "elena123");

        // Admin (solo utente, nessun collegamento)
        creaUtenteSeNonEsiste("admin", "admin123", "admin@email.it", Set.of(Ruolo.ROLE_ADMIN));
    }

    private void assegnaUtenteAStudente(String emailStudente, String username, String password) {
        Optional<Studente> studenteOpt = studenteRepository.findByEmail(emailStudente);
        studenteOpt.ifPresent(studente -> {
            if (studente.getAuthUtente() != null) return; // già associato

            AuthUtente utente = creaUtenteSeNonEsiste(username, password, emailStudente, Set.of(Ruolo.ROLE_STUDENTE));
            studente.setAuthUtente(utente);
            studenteRepository.save(studente);
        });
    }

    private void assegnaUtenteADocente(String emailDocente, String username, String password) {
        Optional<Docente> docenteOpt = docenteRepository.findByEmail(emailDocente);
        docenteOpt.ifPresent(docente -> {
            if (docente.getAuthUtente() != null) return; // già associato

            AuthUtente utente = creaUtenteSeNonEsiste(username, password, emailDocente, Set.of(Ruolo.ROLE_DOCENTE));
            docente.setAuthUtente(utente);
            docenteRepository.save(docente);
        });
    }

    private AuthUtente creaUtenteSeNonEsiste(String username, String rawPassword, String email, Set<Ruolo> ruoliDaAssegnare) {
        return utenteRepository.findByUsername(username)
                .or(() -> utenteRepository.findByEmail(email))
                .orElseGet(() -> {
                    Set<AuthRuolo> ruoli = new HashSet<>();
                    for (Ruolo r : ruoliDaAssegnare) {
                        ruoloRepository.findByNome(r).ifPresent(ruoli::add);
                    }

                    AuthUtente nuovoUtente = AuthUtente.builder()
                            .username(username)
                            .password(passwordEncoder.encode(rawPassword))
                            .email(email)
                            .ruoli(ruoli)
                            .build();

                    return utenteRepository.save(nuovoUtente);
                });
    }
}