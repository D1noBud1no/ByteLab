package com.school.management.controller;

import com.school.management.dto.DocenteDto;
import com.school.management.service.DocenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docenti")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteService docenteService;

    // Crea un nuovo docente
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DocenteDto> crea(@Valid @RequestBody DocenteDto docenteDto) {
        return ResponseEntity.ok(docenteService.crea(docenteDto));
    }

    // Recupera tutti i docenti
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<DocenteDto>> getAll() {
        return ResponseEntity.ok(docenteService.getAll());
    }

    // Recupera un docente per ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<DocenteDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(docenteService.getById(id));
    }

    // Aggiorna un docente esistente
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DocenteDto> aggiorna(@PathVariable Long id, @Valid @RequestBody DocenteDto docenteDto) {
        return ResponseEntity.ok(docenteService.aggiorna(id, docenteDto));
    }

    // Elimina un docente
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        docenteService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
