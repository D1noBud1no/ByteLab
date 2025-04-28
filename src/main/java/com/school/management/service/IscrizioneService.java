package com.school.management.service;

import com.school.management.dto.IscrizioneDto;
import com.school.management.exception.ResourceNotFoundException;
import com.school.management.model.Corso;
import com.school.management.model.Iscrizione;
import com.school.management.model.Studente;
import com.school.management.repository.CorsoRepository;
import com.school.management.repository.IscrizioneRepository;
import com.school.management.repository.StudenteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.school.management.dto.mapper.IscrizioneMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class IscrizioneService {

    private final IscrizioneRepository repository;
    private final StudenteRepository studenteRepository;
    private final CorsoRepository corsoRepository;

    public IscrizioneDto crea(IscrizioneDto dto) {
        Studente studente = studenteRepository.findById(dto.getStudenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Studente non trovato con ID: " + dto.getStudenteId()));
        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));

        Iscrizione salvata = repository.save(fromDto(dto, studente, corso));
        log.info("Iscrizione creata per studente {} al corso {}", studente.getId(), corso.getId());
        return toDto(salvata);
    }

    public List<IscrizioneDto> getAll() {
        List<IscrizioneDto> list = new ArrayList<>();
        for (Iscrizione i : repository.findAll()) {
            list.add(toDto(i));
        }
        log.info("Restituite {} iscrizioni", list.size());
        return list;
    }

    public IscrizioneDto getById(Long id) {
        Iscrizione iscrizione = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Iscrizione non trovata con ID: " + id));
        log.info("Iscrizione trovata con ID {}", id);
        return toDto(iscrizione);
    }

    public IscrizioneDto aggiorna(Long id, IscrizioneDto dto) {
        if (!repository.existsById(id)) {
            log.warn("Aggiornamento fallito: iscrizione con ID {} non trovata", id);
            throw new ResourceNotFoundException("Iscrizione da aggiornare non trovata con ID: " + id);
        }

        Studente studente = studenteRepository.findById(dto.getStudenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Studente non trovato con ID: " + dto.getStudenteId()));
        Corso corso = corsoRepository.findById(dto.getCorsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato con ID: " + dto.getCorsoId()));

        Iscrizione aggiornata = fromDto(dto, studente, corso);
        aggiornata.setId(id);
        Iscrizione salvata = repository.save(aggiornata);
        log.info("Iscrizione aggiornata con ID {}", id);
        return toDto(salvata);
    }

    public void elimina(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Tentativo di eliminazione fallito: iscrizione con ID {} non trovata", id);
            throw new ResourceNotFoundException("Iscrizione da eliminare non trovata con ID: " + id);
        }
        repository.deleteById(id);
        log.info("Iscrizione eliminata con ID {}", id);
    }
}
