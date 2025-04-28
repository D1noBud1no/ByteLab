package com.school.management.service;

import com.school.management.dto.PercorsoDto;
import com.school.management.exception.ResourceNotFoundException;
import com.school.management.model.Percorso;
import com.school.management.repository.PercorsoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.school.management.dto.mapper.PercorsoMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PercorsoService {

    private final PercorsoRepository repository;

    public PercorsoDto crea(PercorsoDto dto) {
        Percorso salvato = repository.save(fromDto(dto));
        log.info("Creato percorso: {}", salvato.getNome());
        return toDto(salvato);
    }

    public List<PercorsoDto> getAll() {
        List<PercorsoDto> list = new ArrayList<>();
        for (Percorso p : repository.findAll()) {
            list.add(toDto(p));
        }
        log.info("Restituiti {} percorsi", list.size());
        return list;
    }

    public PercorsoDto getById(Long id) {
        Percorso percorso = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Percorso non trovato con ID: " + id));
        log.info("Percorso trovato con ID {}: {}", id, percorso.getNome());
        return toDto(percorso);
    }

    public PercorsoDto aggiorna(Long id, PercorsoDto dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: percorso con ID {} non trovato", id);
            throw new ResourceNotFoundException("Percorso da aggiornare non trovato con ID: " + id);
        }
        Percorso aggiornato = fromDto(dto);
        aggiornato.setId(id);
        Percorso salvato = repository.save(aggiornato);
        log.info("Percorso aggiornato con ID {}: {}", id, salvato.getNome());
        return toDto(salvato);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: percorso con ID {} non trovato", id);
            throw new ResourceNotFoundException("Percorso da eliminare non trovato con ID: " + id);
        }

        repository.deleteById(id);
        log.info("Percorso eliminato con ID {}", id);
    }
}
