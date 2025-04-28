package com.school.management.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AulaDto {

    private Long id;

    @NotBlank(message = "Il nome dell'aula non può essere vuoto")
    private String nome;

    @Min(value = 1, message = "La capienza deve essere almeno 1")
    private int capienza;

    @Min(value = 0, message = "Il piano deve essere 0 o superiore")
    private int piano;
}
