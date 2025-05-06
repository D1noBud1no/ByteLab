package com.school.management.controller;

import com.school.management.dto.AulaDto;
import com.school.management.service.AulaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aule")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService aulaService;

    // Recupera tutte le aule
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<AulaDto>> getAllAule() {
        List<AulaDto> aule = aulaService.getAll();
        return ResponseEntity.ok(aule);
    }

    // Recupera un'aula per ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<AulaDto> getAulaById(@PathVariable Long id) {
        AulaDto aula = aulaService.getById(id);
        return ResponseEntity.ok(aula);
    }

    // Crea una nuova aula
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AulaDto> createAula(@Valid @RequestBody AulaDto aulaDto) {
        AulaDto nuovaAula = aulaService.crea(aulaDto);
        return ResponseEntity.status(201).body(nuovaAula);
    }

    // Aggiorna un'aula esistente
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AulaDto> updateAula(@PathVariable Long id, @Valid @RequestBody AulaDto aulaDto) {
        AulaDto aulaAggiornata = aulaService.aggiorna(id, aulaDto);
        return ResponseEntity.ok(aulaAggiornata);
    }

    // Elimina un'aula
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAula(@PathVariable Long id) {
        aulaService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
