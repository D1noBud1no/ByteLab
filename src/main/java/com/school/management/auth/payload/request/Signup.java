package com.school.management.auth.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

/**
 * Classe per gestire i dati della richiesta di registrazione.
 */
@Data
public class Signup {

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @Past(message = "La data di nascita deve essere nel passato")
    private LocalDate dataNascita;

    @NotBlank(message = "Il nome utente è obbligatorio")
    private String username;

    @Email(message = "Email non valida")
    @NotBlank(message = "L'email è obbligatoria")
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    private String password;

    private Set<String> ruoli; // es. ["admin", "studente"]
}
