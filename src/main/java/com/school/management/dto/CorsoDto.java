package com.school.management.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorsoDto {

    private Long id;

    @NotBlank(message = "Il nome del corso non può essere vuoto")
    private String nome;

    @NotNull(message = "La data di inizio non può essere nulla")
    private LocalDate dataInizio;

    @NotNull(message = "La data di fine non può essere nulla")
    private LocalDate dataFine;

    @Min(value = 1, message = "La durata deve essere almeno 1 ora")
    private int durataOre;

    @NotNull(message = "L'ID del docente non può essere nullo")
    private Long docenteId;

    @NotNull(message = "L'ID dell'aula non può essere nullo")
    private Long aulaId;

    @NotNull(message = "L'ID del percorso formativo non può essere nullo")
    private Long percorsoId;
}