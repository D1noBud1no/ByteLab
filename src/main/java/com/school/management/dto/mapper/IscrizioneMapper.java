package com.school.management.dto.mapper;

import com.school.management.dto.IscrizioneDto;
import com.school.management.model.Corso;
import com.school.management.model.Iscrizione;
import com.school.management.model.Studente;

public class IscrizioneMapper {

    // Metodo per convertire un'entità Iscrizione in un DTO IscrizioneDto
    public static IscrizioneDto toDto(Iscrizione i) {
        if (i == null) return null;

        return IscrizioneDto.builder()
                .id(i.getId())
                .dataIscrizione(i.getDataIscrizione())
                .stato(i.getStato().name())
                .voto(i.getVoto())
                .studenteId(i.getStudente() != null ? i.getStudente().getId() : null)
                .corsoId(i.getCorso() != null ? i.getCorso().getId() : null)
                .build();
    }

    // Metodo per convertire un DTO IscrizioneDto in un'entità Iscrizione
    public static Iscrizione fromDto(IscrizioneDto dto, Studente studente, Corso corso) {
        if (dto == null) return null;

        return Iscrizione.builder()
                .id(dto.getId())
                .dataIscrizione(dto.getDataIscrizione())
                .stato(Iscrizione.StatoIscrizione.valueOf(dto.getStato().toUpperCase()))
                .voto(dto.getVoto())
                .studente(studente)
                .corso(corso)
                .build();
    }
}
