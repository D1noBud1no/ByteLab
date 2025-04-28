package com.school.management.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "percorsi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Percorso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;

    @OneToMany(mappedBy = "percorso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Corso> corsi = new ArrayList<>();

    public List<Corso> getCorsi() {
        return corsi;
    }
}