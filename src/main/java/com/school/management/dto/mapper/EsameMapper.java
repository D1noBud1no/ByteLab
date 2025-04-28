package com.school.management.dto.mapper;

import com.school.management.dto.EsameDto;
import com.school.management.model.Corso;
import com.school.management.model.Esame;

public class EsameMapper {

    // Metodo per convertire un'entità Esame in un DTO EsameDto
    public static EsameDto toDto(Esame esame) {
        if (esame == null) return null;

        return EsameDto.builder()
                .id(esame.getId())
                .nome(esame.getNome())
                .data(esame.getData())
                .corsoId(esame.getCorso() != null ? esame.getCorso().getId() : null)
                .build();
    }

    // Metodo per convertire un DTO EsameDto in un'entità Esame
    public static Esame fromDto(EsameDto dto, Corso corso) {
        if (dto == null) return null;

        return Esame.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .data(dto.getData())
                .corso(corso)
                .build();
    }
}
