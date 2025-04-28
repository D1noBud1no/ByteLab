package com.school.management.service;

import com.school.management.dto.AulaDto;
import com.school.management.exception.ResourceNotFoundException;
import com.school.management.model.Aula;
import com.school.management.repository.AulaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.school.management.dto.mapper.AulaMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AulaService {

    private final AulaRepository repository;

    public AulaDto crea(AulaDto dto) {
        Aula nuova = repository.save(fromDto(dto));
        log.info("Creata aula: {}", nuova.getNome());
        return toDto(nuova);
    }

    public List<AulaDto> getAll() {
        List<AulaDto> list = new ArrayList<>();
        for (Aula a : repository.findAll()) {
            list.add(toDto(a));
        }
        log.info("Restituite {} aule", list.size());
        return list;
    }

    public AulaDto getById(Long id) {
        Aula aula = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula non trovata con ID: " + id));
        log.info("Aula trovata con ID {}: {}", id, aula.getNome());
        return toDto(aula);
    }

    public AulaDto aggiorna(Long id, AulaDto dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: aula con ID {} non trovata", id);
            throw new ResourceNotFoundException("Aula da aggiornare non trovata con ID: " + id);
        }

        Aula aggiornata = fromDto(dto);
        aggiornata.setId(id);
        Aula salvata = repository.save(aggiornata);
        log.info("Aula aggiornata con ID {}: {}", id, salvata.getNome());
        return toDto(salvata);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: aula con ID {} non trovata", id);
            throw new ResourceNotFoundException("Aula da eliminare non trovata con ID: " + id);
        }

        repository.deleteById(id);
        log.info("Aula eliminata con ID {}", id);
    }
}
