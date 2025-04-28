package com.school.management.auth.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe per rappresentare una risposta con un messaggio.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
    private String message;
}
