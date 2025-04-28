package com.school.management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PercorsoDto {

    private Long id;

    @NotBlank(message = "Il nome del percorso non può essere vuoto")
    private String nome;

    @NotBlank(message = "La descrizione del percorso non può essere vuota")
    private String descrizione;
}