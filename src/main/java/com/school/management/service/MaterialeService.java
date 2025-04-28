package com.school.management.service;

import com.school.management.dto.MaterialeDto;
import com.school.management.exception.ResourceNotFoundException;
import com.school.management.model.Corso;
import com.school.management.model.Materiale;
import com.school.management.repository.CorsoRepository;
import com.school.management.repository.MaterialeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.school.management.dto.mapper.MaterialeMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaterialeService {

    private final MaterialeRepository repository;
    private final CorsoRepository corsoRepository;

    public MaterialeDto crea(MaterialeDto dto) {
        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));

        Materiale salvato = repository.save(fromDto(dto, corso));
        log.info("Creato nuovo materiale didattico per il corso {}: {}", corso.getId(), salvato.getTitolo());
        return toDto(salvato);
    }

    public List<MaterialeDto> getAll() {
        List<MaterialeDto> list = new ArrayList<>();
        for (Materiale m : repository.findAll()) {
            list.add(toDto(m));
        }
        log.info("Restituiti {} materiali didattici", list.size());
        return list;
    }

    public MaterialeDto getById(Long id) {
        Materiale materiale = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Materiale didattico non trovato con ID: " + id));
        log.info("Materiale didattico trovato con ID {}: {}", id, materiale.getTitolo());
        return toDto(materiale);
    }

    public MaterialeDto aggiorna(Long id, MaterialeDto dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: materiale con ID {} non trovato", id);
            throw new ResourceNotFoundException("Materiale didattico da aggiornare non trovato con ID: " + id);
        }

        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));

        Materiale aggiornato = fromDto(dto, corso);
        aggiornato.setId(id);
        Materiale salvato = repository.save(aggiornato);
        log.info("Materiale didattico aggiornato con ID {}: {}", id, salvato.getTitolo());
        return toDto(salvato);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: materiale con ID {} non trovato", id);
            throw new ResourceNotFoundException("Materiale didattico da eliminare non trovato con ID: " + id);
        }

        repository.deleteById(id);
        log.info("Materiale didattico eliminato con ID {}", id);
    }
}
