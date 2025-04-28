package com.school.management.dto.mapper;

import com.school.management.dto.CorsoDto;
import com.school.management.model.Aula;
import com.school.management.model.Corso;
import com.school.management.model.Docente;
import com.school.management.model.Percorso;

public class CorsoMapper {

    // Metodo per convertire un'entità Corso in un DTO CorsoDto
    public static CorsoDto toDto(Corso corso) {
        if (corso == null) return null;

        CorsoDto dto = new CorsoDto();
        dto.setId(corso.getId());
        dto.setNome(corso.getNome());
        dto.setDataInizio(corso.getDataInizio());
        dto.setDataFine(corso.getDataFine());
        dto.setDurataOre(corso.getDurataOre());
        dto.setDocenteId(corso.getDocente() != null ? corso.getDocente().getId() : null);
        dto.setAulaId(corso.getAula() != null ? corso.getAula().getId() : null);
        dto.setPercorsoId(corso.getPercorso() != null ? corso.getPercorso().getId() : null);
        return dto;
    }

    // Metodo per convertire un DTO CorsoDto in un'entità Corso
    public static Corso fromDto(CorsoDto dto, Docente docente, Aula aula, Percorso percorso) {
        if (dto == null) return null;

        return Corso.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .dataInizio(dto.getDataInizio())
                .dataFine(dto.getDataFine())
                .durataOre(dto.getDurataOre())
                .docente(docente)
                .aula(aula)
                .percorso(percorso)
                .build();
    }
}
