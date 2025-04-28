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
public class IscrizioneDto {

    private Long id;

    @NotNull(message = "La data di iscrizione non può essere nulla")
    private LocalDate dataIscrizione;

    @NotBlank(message = "Lo stato dell'iscrizione non può essere vuoto")
    @Pattern(regexp = "ATTIVA|COMPLETATA|RITIRATA", message = "Stato non valido (ATTIVA, COMPLETATA o RITIRATA)")
    private String stato;

    @Min(value = 18, message = "Il voto minimo è 18")
    @Max(value = 30, message = "Il voto massimo è 30")
    private Integer voto;

    @NotNull(message = "L'ID dello studente non può essere nullo")
    private Long studenteId;

    @NotNull(message = "L'ID del corso non può essere nullo")
    private Long corsoId;
}
