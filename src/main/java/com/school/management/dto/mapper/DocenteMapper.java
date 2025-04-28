package com.school.management.dto.mapper;

import com.school.management.dto.DocenteDto;
import com.school.management.model.Docente;

public class DocenteMapper {

    // Metodo per convertire un'entità Docente in un DTO DocenteDto
    public static DocenteDto toDto(Docente docente) {
        if (docente == null) return null;

        return DocenteDto.builder()
                .id(docente.getId())  
                .nome(docente.getNome())
                .cognome(docente.getCognome())
                .email(docente.getEmail())
                .build();
    }

    // Metodo per convertire un DTO DocenteDto in un'entità Docente
    public static Docente fromDto(DocenteDto dto) {
        if (dto == null) return null;

        return Docente.builder()
                .id(dto.getId())// Assicurati che dto sia di tipo DocenteDto
                .nome(dto.getNome())
                .cognome(dto.getCognome())
                .email(dto.getEmail())
                .build();
    }
}