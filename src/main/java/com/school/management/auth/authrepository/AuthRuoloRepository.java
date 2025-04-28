package com.school.management.auth.authrepository;

import com.school.management.auth.authmodel.AuthRuolo;
import com.school.management.auth.authmodel.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRuoloRepository extends JpaRepository<AuthRuolo, Integer> {
    Optional<AuthRuolo> findByNome(Ruolo nome);

    boolean existsByNome(Ruolo nome);
}
