package com.school.management.service;

import com.school.management.dto.CorsoDto;
import com.school.management.exception.ResourceNotFoundException;
import com.school.management.model.Corso;
import com.school.management.model.Aula;
import com.school.management.model.Docente;
import com.school.management.model.Percorso;
import com.school.management.repository.AulaRepository;
import com.school.management.repository.CorsoRepository;
import com.school.management.repository.DocenteRepository;
import com.school.management.repository.PercorsoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.school.management.dto.mapper.CorsoMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CorsoService {

    private final CorsoRepository corsoRepository;
    private final DocenteRepository docenteRepository;
    private final AulaRepository aulaRepository;
    private final PercorsoRepository percorsoRepository;

    public CorsoDto crea(CorsoDto dto) {
        Docente docente = docenteRepository.findById(dto.getDocenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Docente non trovato con ID: " + dto.getDocenteId()));

        Aula aula = aulaRepository.findById(dto.getAulaId())
                .orElseThrow(() -> new ResourceNotFoundException("Aula non trovata con ID: " + dto.getAulaId()));

        Percorso percorso = percorsoRepository.findById(dto.getPercorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Percorso non trovato con ID: " + dto.getPercorsoId()));

        Corso corso = fromDto(dto, docente, aula, percorso);
        Corso salvato = corsoRepository.save(corso);
        log.info("Creato nuovo corso: {}", corso.getNome());
        return toDto(salvato);
    }

    public List<CorsoDto> getAll() {
        List<CorsoDto> list = new ArrayList<>();
        for (Corso c : corsoRepository.findAll()) {
            list.add(toDto(c));
        }
        log.info("Restituiti {} corsi", list.size());
        return list;
    }

    public CorsoDto getById(Long id) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + id));
        log.info("Corso trovato con ID {}: {}", id, corso.getNome());
        return toDto(corso);
    }

    public CorsoDto aggiorna(Long id, CorsoDto dto) {
        if (!corsoRepository.existsById(id)) {
            log.warn("Aggiornamento fallito: corso con ID {} non trovato", id);
            throw new ResourceNotFoundException("Corso da aggiornare non trovato con ID: " + id);
        }

        Docente docente = docenteRepository.findById(dto.getDocenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Docente non trovato con ID: " + dto.getDocenteId()));

        Aula aula = aulaRepository.findById(dto.getAulaId())
                .orElseThrow(() -> new ResourceNotFoundException("Aula non trovata con ID: " + dto.getAulaId()));

        Percorso percorso = percorsoRepository.findById(dto.getPercorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Percorso non trovato con ID: " + dto.getPercorsoId()));

        Corso aggiornato = fromDto(dto, docente, aula, percorso);
        aggiornato.setId(id);
        Corso salvato = corsoRepository.save(aggiornato);
        log.info("Corso aggiornato con ID {}: {}", id, salvato.getNome());
        return toDto(salvato);
    }

    public void elimina(Long id) {
        if (!corsoRepository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: corso con ID {} non trovato", id);
            throw new ResourceNotFoundException("Corso da eliminare non trovato con ID: " + id);
        }
        corsoRepository.deleteById(id);
        log.info("Corso eliminato con ID {}", id);
    }
}
