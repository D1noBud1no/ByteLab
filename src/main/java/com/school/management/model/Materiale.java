package com.school.management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materiale")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Materiale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;

    public enum Tipo {
        PDF, LINK, SLIDE
    }
}

