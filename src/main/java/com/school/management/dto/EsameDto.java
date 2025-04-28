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
public class EsameDto {

    private Long id;

    @NotBlank(message = "Il nome dell'esame non può essere vuoto")
    private String nome;

    @NotNull(message = "La data dell'esame non può essere nulla")
    private LocalDate data;

    @NotNull(message = "L'ID del corso non può essere nullo")
    private Long corsoId;
}