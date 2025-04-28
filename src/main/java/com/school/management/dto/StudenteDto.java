package com.school.management.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudenteDto {

    private Long id;

    @NotBlank(message = "Il nome dello studente non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il cognome dello studente non può essere vuoto")
    private String cognome;

    @Email(message = "L'email dello studente non è valida")
    @NotBlank(message = "L'email dello studente non può essere vuota")
    private String email;

    @NotNull(message = "La data di nascita dello studente non può essere nulla")
    private LocalDate dataNascita;
}
