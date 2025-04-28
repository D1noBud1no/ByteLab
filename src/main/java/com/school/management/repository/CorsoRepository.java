package com.school.management.repository;

import com.school.management.model.Corso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorsoRepository extends JpaRepository<Corso, Long> {
    Optional<Corso> findByNome(String nome);
}
