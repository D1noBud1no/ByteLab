package com.school.management.controller;

import com.school.management.dto.IscrizioneDto;
import com.school.management.service.IscrizioneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/iscrizioni")
@RequiredArgsConstructor
public class IscrizioniController {

    private final IscrizioneService iscrizioneService;

    // Crea una nuova iscrizione
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<IscrizioneDto> crea(@Valid @RequestBody IscrizioneDto iscrizioneDto) {
        return ResponseEntity.ok(iscrizioneService.crea(iscrizioneDto));
    }

    // Recupera tutte le iscrizioni
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<IscrizioneDto>> getAll() {
        return ResponseEntity.ok(iscrizioneService.getAll());
    }

    // Recupera un'iscrizione per ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<IscrizioneDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(iscrizioneService.getById(id));
    }

    // Aggiorna un'iscrizione esistente
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<IscrizioneDto> aggiorna(@PathVariable Long id, @Valid @RequestBody IscrizioneDto iscrizioneDto) {
        return ResponseEntity.ok(iscrizioneService.aggiorna(id, iscrizioneDto));
    }

    // Elimina un'iscrizione
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        iscrizioneService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
