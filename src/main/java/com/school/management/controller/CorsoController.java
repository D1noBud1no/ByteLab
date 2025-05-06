package com.school.management.controller;

import com.school.management.dto.CorsoDto;
import com.school.management.service.CorsoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corsi")
@RequiredArgsConstructor
public class CorsoController {

    private final CorsoService corsoService;

    // Crea un nuovo corso
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CorsoDto> crea(@Valid @RequestBody CorsoDto corsoDto) {
        return ResponseEntity.ok(corsoService.crea(corsoDto));
    }

    // Recupera tutti i corsi
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<CorsoDto>> getAll() {
        return ResponseEntity.ok(corsoService.getAll());
    }

    // Recupera un corso per ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<CorsoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(corsoService.getById(id));
    }

    // Aggiorna un corso esistente
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CorsoDto> aggiorna(@PathVariable Long id, @Valid @RequestBody CorsoDto corsoDto) {
        return ResponseEntity.ok(corsoService.aggiorna(id, corsoDto));
    }

    // Elimina un corso
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        corsoService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}