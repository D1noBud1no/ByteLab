package com.school.management.auth.authmodel;

import com.school.management.model.Docente;
import com.school.management.model.Studente;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUtente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utente_ruoli",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    @Builder.Default
    private Set<AuthRuolo> ruoli = new HashSet<>();

    @OneToOne(mappedBy = "authUtente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Studente studente;

    @OneToOne(mappedBy = "authUtente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Docente docente;
}
