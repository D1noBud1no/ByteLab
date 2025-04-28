package com.school.management.service;

import com.school.management.dto.StudenteDto;
import com.school.management.exception.ResourceNotFoundException;
import com.school.management.model.Studente;
import com.school.management.repository.StudenteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.school.management.dto.mapper.StudenteMapper.*;

// Questo Service implementa il Service Layer Pattern.
// Contiene la logica di business per l'entità Studente, separando il controller dal repository.

@Service
@RequiredArgsConstructor
@Slf4j
public class StudenteService {

    private final StudenteRepository repository;

    public StudenteDto creaStudente(StudenteDto dto) {
        Studente nuovo = repository.save(fromDto(dto));
        log.info("Creato nuovo studente: {}", nuovo.getEmail());
        return toDto(nuovo);
    }

    public List<StudenteDto> getAll() {
        List<Studente> studenti = repository.findAll();
        List<StudenteDto> lista = new ArrayList<>();

        for (Studente s : studenti) {
            lista.add(toDto(s));
        }

        log.info("Recuperati {} studenti", lista.size());
        return lista;
    }

    public StudenteDto getById(Long id) {
        Studente studente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Studente non trovato con ID: " + id));
        log.info("Studente trovato con ID {}: {}", id, studente.getEmail());
        return toDto(studente);
    }

    public StudenteDto aggiornaStudente(Long id, StudenteDto dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: studente con ID {} non trovato", id);
            throw new ResourceNotFoundException("Studente da aggiornare non trovato con ID: " + id);
        }

        Studente daAggiornare = fromDto(dto);
        daAggiornare.setId(id);
        Studente aggiornato = repository.save(daAggiornare);
        log.info("Studente aggiornato con ID {}: {}", id, aggiornato.getEmail());
        return toDto(aggiornato);
    }

    public void eliminaStudente(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: studente con ID {} non trovato", id);
            throw new ResourceNotFoundException("Studente da eliminare non trovato con ID: " + id);
        }

        repository.deleteById(id);
        log.info("Studente eliminato con ID {}", id);
    }
}

