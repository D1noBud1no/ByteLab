package com.school.management.model;

import com.school.management.auth.authmodel.AuthUtente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "studenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Studente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;

    @OneToMany(mappedBy = "studente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Iscrizione> iscrizioni;

    @OneToOne
    @JoinColumn(name = "auth_utente_id", unique = true)
    private AuthUtente authUtente;
}
