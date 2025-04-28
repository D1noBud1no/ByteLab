package com.school.management.dto.mapper;

import com.school.management.dto.PercorsoDto;
import com.school.management.model.Percorso;

public class PercorsoMapper {

    // Metodo per convertire un'entità Percorso in un DTO PercorsoDto
    public static PercorsoDto toDto(Percorso percorso) {
        if (percorso == null) return null;

        return PercorsoDto.builder()
                .id(percorso.getId())
                .nome(percorso.getNome())
                .descrizione(percorso.getDescrizione())
                .build();
    }

    // Metodo per convertire un DTO PercorsoDto in un'entità Percorso
    public static Percorso fromDto(PercorsoDto dto) {
        if (dto == null) return null;

        return Percorso.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .descrizione(dto.getDescrizione())
                .build();
    }
}

