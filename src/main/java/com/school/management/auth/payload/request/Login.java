package com.school.management.auth.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Classe per gestire i dati della richiesta di login.
 */
@Data
public class Login {

    @NotBlank(message = "nome utente obbligatorio")
    private String username;

    @NotBlank(message = "password obbligatoria")
    private String password;
}
