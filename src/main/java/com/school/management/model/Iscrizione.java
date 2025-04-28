package com.school.management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "iscrizioni")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Iscrizione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataIscrizione;

    @Enumerated(EnumType.STRING)
    private StatoIscrizione stato;

    private Integer voto;

    @ManyToOne
    @JoinColumn(name = "studente_id")
    private Studente studente;

    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;

    public enum StatoIscrizione {
        ATTIVA,
        COMPLETATA,
        RITIRATA
    }
}
