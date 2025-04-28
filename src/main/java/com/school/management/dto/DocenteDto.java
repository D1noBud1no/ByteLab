package com.school.management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocenteDto {

    private Long id;

    @NotBlank(message = "Il nome del docente non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il cognome del docente non può essere vuoto")
    private String cognome;

    @NotBlank(message = "L'email del docente non può essere vuota")
    @Email(message = "L'email del docente non è valida")
    private String email;
}
