package com.school.management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "esami")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Esame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;
}
