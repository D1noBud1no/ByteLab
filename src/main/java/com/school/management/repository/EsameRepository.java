package com.school.management.repository;

import com.school.management.model.Esame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EsameRepository extends JpaRepository<Esame, Long> {
    Optional<Esame> findByNome(String nome);
}
