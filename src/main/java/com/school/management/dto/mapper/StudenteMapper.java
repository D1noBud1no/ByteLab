package com.school.management.dto.mapper;

import com.school.management.dto.StudenteDto;
import com.school.management.model.Studente;

public class StudenteMapper {

    // Metodo per convertire un'entità Studente in un DTO StudenteDto
    public static StudenteDto toDto(Studente studente) {
        if (studente == null) return null;

        return StudenteDto.builder()
                .id(studente.getId())
                .nome(studente.getNome())
                .cognome(studente.getCognome())
                .email(studente.getEmail())
                .dataNascita(studente.getDataNascita())
                .build();
    }

    // Metodo per convertire un DTO StudenteDto in un'entità Studente
    public static Studente fromDto(StudenteDto dto) {
        if (dto == null) return null;

        return Studente.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cognome(dto.getCognome())
                .email(dto.getEmail())
                .dataNascita(dto.getDataNascita())
                .build();
    }
}

