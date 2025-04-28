package com.school.management.dto.mapper;

import com.school.management.dto.MaterialeDto;
import com.school.management.model.Corso;
import com.school.management.model.Materiale;

public class MaterialeMapper {

    // Metodo per convertire un'entità Materiale in un DTO MaterialeDto
    public static MaterialeDto toDto(Materiale m) {
        if (m == null) return null;

        return MaterialeDto.builder()
                .id(m.getId())
                .titolo(m.getTitolo())
                .tipo(m.getTipo() != null ? m.getTipo().name() : null)
                .descrizione(m.getDescrizione())
                .corsoId(m.getCorso() != null ? m.getCorso().getId() : null)
                .build();
    }

    // Metodo per convertire un DTO MaterialeDto in un'entità Materiale
    public static Materiale fromDto(MaterialeDto dto, Corso corso) {
        if (dto == null) return null;

        return Materiale.builder()
                .id(dto.getId())
                .titolo(dto.getTitolo())
                .tipo(Materiale.Tipo.valueOf(dto.getTipo().toUpperCase()))
                .descrizione(dto.getDescrizione())
                .corso(corso)
                .build();
    }
}
