package com.school.management.service;

import com.school.management.dto.EsameDto;
import com.school.management.exception.ResourceNotFoundException;
import com.school.management.model.Corso;
import com.school.management.model.Esame;
import com.school.management.repository.CorsoRepository;
import com.school.management.repository.EsameRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.school.management.dto.mapper.EsameMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class EsameService {

    private final EsameRepository repository;
    private final CorsoRepository corsoRepository;

    public EsameDto crea(EsameDto dto) {
        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));
        Esame salvato = repository.save(fromDto(dto, corso));
        log.info("Creato nuovo esame: {}", salvato.getNome());
        return toDto(salvato);
    }

    public List<EsameDto> getAll() {
        List<EsameDto> list = new ArrayList<>();
        for (Esame e : repository.findAll()) {
            list.add(toDto(e));
        }
        log.info("Restituiti {} esami", list.size());
        return list;
    }

    public EsameDto getById(Long id) {
        Esame esame = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Esame non trovato con ID: " + id));
        log.info("Esame trovato con ID {}: {}", id, esame.getNome());
        return toDto(esame);
    }

    public EsameDto aggiorna(Long id, EsameDto dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: esame con ID {} non trovato", id);
            throw new ResourceNotFoundException("Esame da aggiornare non trovato con ID: " + id);
        }

        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));

        Esame aggiornato = fromDto(dto, corso);
        aggiornato.setId(id);
        Esame salvato = repository.save(aggiornato);
        log.info("Esame aggiornato con ID {}: {}", id, salvato.getNome());
        return toDto(salvato);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: esame con ID {} non trovato", id);
            throw new ResourceNotFoundException("Esame da eliminare non trovato con ID: " + id);
        }

        repository.deleteById(id);
        log.info("Esame eliminato con ID {}", id);
    }
}
