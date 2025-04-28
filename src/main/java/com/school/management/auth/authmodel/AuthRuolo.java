package com.school.management.auth.authmodel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ruoli")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRuolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = true)
    private Ruolo nome;
}
