package com.school.management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int capienza;
    private int piano;
}

