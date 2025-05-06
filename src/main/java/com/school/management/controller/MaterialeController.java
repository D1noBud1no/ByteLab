package com.school.management.controller;

import com.school.management.dto.MaterialeDto;
import com.school.management.service.MaterialeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiali")
@RequiredArgsConstructor
public class MaterialeController {

    private final MaterialeService materialeService;

    // Crea un nuovo materiale
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MaterialeDto> crea(@Valid @RequestBody MaterialeDto materialeDto) {
        return ResponseEntity.ok(materialeService.crea(materialeDto));
    }

    // Recupera tutti i materiali
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<MaterialeDto>> getAll() {
        return ResponseEntity.ok(materialeService.getAll());
    }

    // Recupera un materiale per ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<MaterialeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(materialeService.getById(id));
    }

    // Aggiorna un materiale esistente
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MaterialeDto> aggiorna(@PathVariable Long id, @Valid @RequestBody MaterialeDto materialeDto) {
        return ResponseEntity.ok(materialeService.aggiorna(id, materialeDto));
    }

    // Elimina un materiale
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        materialeService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}