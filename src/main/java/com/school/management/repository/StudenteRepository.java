package com.school.management.repository;

import com.school.management.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudenteRepository extends JpaRepository<Studente, Long> {
    Optional<Studente> findByEmail(String email);
}
