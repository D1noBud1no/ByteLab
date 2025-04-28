package com.school.management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "corsi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private int durataOre;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "percorso_id") // Modificato per riflettere il nome corretto
    private Percorso percorso;

    @OneToMany(mappedBy = "corso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Materiale> materialiDidattici = new ArrayList<>();

    @OneToMany(mappedBy = "corso")
    private List<Iscrizione> iscrizioni;

    @OneToMany(mappedBy = "corso")
    private List<Esame> esami;
}
