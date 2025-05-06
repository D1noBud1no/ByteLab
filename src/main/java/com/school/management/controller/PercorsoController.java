package com.school.management.controller;

import com.school.management.dto.PercorsoDto;
import com.school.management.service.PercorsoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/percorsi")
@RequiredArgsConstructor
public class PercorsoController {

    private final PercorsoService percorsoService;

    // Crea un nuovo percorso
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PercorsoDto> crea(@Valid @RequestBody PercorsoDto percorsoDto) {
        return ResponseEntity.ok(percorsoService.crea(percorsoDto));
    }

    // Recupera tutti i percorsi
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<PercorsoDto>> getAll() {
        return ResponseEntity.ok(percorsoService.getAll());
    }

    // Recupera un percorso per ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<PercorsoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(percorsoService.getById(id));
    }

    // Aggiorna un percorso esistente
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PercorsoDto> aggiorna(@PathVariable Long id, @Valid @RequestBody PercorsoDto percorsoDto) {
        return ResponseEntity.ok(percorsoService.aggiorna(id, percorsoDto));
    }

    // Elimina un percorso
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        percorsoService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
