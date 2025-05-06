package com.school.management.controller;

import com.school.management.dto.StudenteDto;
import com.school.management.service.StudenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studenti")
@RequiredArgsConstructor
public class StudenteController {

    private final StudenteService studenteService;

    // Crea un nuovo studente
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudenteDto> crea(@Valid @RequestBody StudenteDto studenteDto) {
        return ResponseEntity.ok(studenteService.creaStudente(studenteDto));
    }

    // Recupera tutti gli studenti
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<StudenteDto>> getAll() {
        return ResponseEntity.ok(studenteService.getAll());
    }

    // Recupera uno studente per ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<StudenteDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studenteService.getById(id));
    }

    // Aggiorna uno studente esistente
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudenteDto> aggiorna(@PathVariable Long id, @Valid @RequestBody StudenteDto studenteDto) {
        return ResponseEntity.ok(studenteService.aggiornaStudente(id, studenteDto));
    }

    // Elimina uno studente
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        studenteService.eliminaStudente(id);
        return ResponseEntity.noContent().build();
    }
}