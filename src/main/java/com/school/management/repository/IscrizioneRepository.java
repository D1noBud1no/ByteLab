package com.school.management.repository;

import com.school.management.model.Corso;
import com.school.management.model.Iscrizione;
import com.school.management.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IscrizioneRepository extends JpaRepository<Iscrizione, Long> {
    List<Iscrizione> findByStudenteId(Long studenteId);
    Optional<Iscrizione> findByStudenteAndCorso(Studente studente, Corso corso);
}
