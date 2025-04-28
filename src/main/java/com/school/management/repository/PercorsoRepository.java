package com.school.management.repository;

import com.school.management.model.Percorso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PercorsoRepository extends JpaRepository<Percorso, Long> {
    Optional<Percorso> findByNome(String nome);
}

