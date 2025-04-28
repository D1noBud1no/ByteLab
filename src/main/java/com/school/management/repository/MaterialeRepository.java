package com.school.management.repository;

import com.school.management.model.Materiale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialeRepository extends JpaRepository<Materiale, Long> {
    Optional<Materiale> findByTitolo(String titolo);
}
