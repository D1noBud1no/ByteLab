package com.school.management.service;

import com.school.management.dto.DocenteDto;
import com.school.management.exception.ResourceNotFoundException;
import com.school.management.model.Docente;
import com.school.management.repository.DocenteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.school.management.dto.mapper.DocenteMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocenteService {

    private final DocenteRepository repository;

    public DocenteDto crea(DocenteDto dto) {
        Docente nuovo = repository.save(fromDto(dto));
        log.info("Creato nuovo docente: {}", nuovo.getEmail());
        return toDto(nuovo);
    }

    public List<DocenteDto> getAll() {
        List<DocenteDto> lista = new ArrayList<>();
        for (Docente d : repository.findAll()) {
            lista.add(toDto(d));
        }
        log.info("Recuperati {} docenti", lista.size());
        return lista;
    }

    public DocenteDto getById(Long id) {
        Docente d = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente non trovato con ID: " + id));
        log.info("Docente trovato con ID {}: {}", id, d.getEmail());
        return toDto(d);
    }

    public DocenteDto aggiorna(Long id, DocenteDto dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: docente con ID {} non trovato", id);
            throw new ResourceNotFoundException("Docente da aggiornare non trovato con ID: " + id);
        }
        Docente aggiornato = fromDto(dto);
        aggiornato.setId(id);
        Docente salvato = repository.save(aggiornato);
        log.info("Docente aggiornato con ID {}: {}", id, salvato.getEmail());
        return toDto(salvato);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: docente con ID {} non trovato", id);
            throw new ResourceNotFoundException("Docente da eliminare non trovato con ID: " + id);
        }
        repository.deleteById(id);
        log.info("Docente eliminato con ID {}", id);
    }
}
