package com.school.management.auth.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Classe per rappresentare la risposta JWT dopo un'autenticazione.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseJwt {
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> ruoli;
}
