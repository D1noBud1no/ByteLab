package com.school.management.controller;

import com.school.management.dto.EsameDto;
import com.school.management.service.EsameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/esami")
@RequiredArgsConstructor
public class EsameController {

    private final EsameService esameService;

    // Crea un nuovo esame
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EsameDto> crea(@Valid @RequestBody EsameDto esameDto) {
        return ResponseEntity.ok(esameService.crea(esameDto));
    }

    // Recupera tutti gli esami
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<EsameDto>> getAll() {
        return ResponseEntity.ok(esameService.getAll());
    }

    // Recupera un esame per ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<EsameDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(esameService.getById(id));
    }

    // Aggiorna un esame esistente
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EsameDto> aggiorna(@PathVariable Long id, @Valid @RequestBody EsameDto esameDto) {
        return ResponseEntity.ok(esameService.aggiorna(id, esameDto));
    }

    // Elimina un esame
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        esameService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}