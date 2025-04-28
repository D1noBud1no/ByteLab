package com.school.management.dto.mapper;

import com.school.management.dto.AulaDto;
import com.school.management.model.Aula;

public class AulaMapper {

    // Metodo per convertire un'entità Aula in un DTO AulaDto
    public static AulaDto toDto(Aula aula) {
        if (aula == null) return null;

        return AulaDto.builder()
                .id(aula.getId())
                .nome(aula.getNome())
                .capienza(aula.getCapienza())
                .piano(aula.getPiano())
                .build();
    }

    // Metodo per convertire un DTO AulaDto in un'entità Aula
    public static Aula fromDto(AulaDto dto) {
        if (dto == null) return null;

        return Aula.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .capienza(dto.getCapienza())
                .piano(dto.getPiano())
                .build();
    }
}
