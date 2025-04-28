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
public class MaterialeDto {

    private Long id;

    @NotBlank(message = "Il titolo del materiale non può essere vuoto")
    private String titolo;

    @NotBlank(message = "Il tipo del materiale non può essere vuoto")
    @Pattern(regexp = "PDF|LINK|SLIDE", message = "Il tipo deve essere PDF, LINK o SLIDE")
    private String tipo;

    @NotBlank(message = "La descrizione del materiale non può essere vuota")
    private String descrizione;

    @NotNull(message = "L'ID del corso non può essere nullo")
    private Long corsoId;
}