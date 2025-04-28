package com.school.management.repository;

import com.school.management.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AulaRepository extends JpaRepository<Aula, Long> {
    Optional<Aula> findByNome(String nome);
}
