package com.school.management.auth.authcontroller;

import com.school.management.auth.authmodel.AuthRuolo;
import com.school.management.auth.authmodel.AuthUtente;
import com.school.management.auth.authmodel.Ruolo;
import com.school.management.auth.payload.request.Login;
import com.school.management.auth.payload.request.Signup;
import com.school.management.auth.payload.response.ResponseJwt;
import com.school.management.auth.payload.response.ResponseMessage;
import com.school.management.auth.authrepository.AuthRuoloRepository;
import com.school.management.auth.authrepository.AuthUtenteRepository;
import com.school.management.auth.security.JwtUtils;
import com.school.management.auth.security.UserImpl;
import com.school.management.model.Docente;
import com.school.management.model.Studente;
import com.school.management.repository.DocenteRepository;
import com.school.management.repository.StudenteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthUtenteRepository utenteRepository;
    private final AuthRuoloRepository ruoloRepository;
    private final StudenteRepository studenteRepository;
    private final DocenteRepository docenteRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    // Gestione del login
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserImpl userDetails = (UserImpl) authentication.getPrincipal();

        List<String> ruoli = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ResponseJwt(jwt, "Bearer", userDetails.getUsername(), ruoli));
    }

    // Gestione della registrazione
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Signup signUpRequest) {
        if (utenteRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage("Errore: Username già in uso!"));
        }

        if (utenteRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage("Errore: Email già in uso!"));
        }

        // Creazione di un nuovo utente
        AuthUtente utente = AuthUtente.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();

        Set<AuthRuolo> ruoli = assignRoles(signUpRequest.getRuoli());
        utente.setRuoli(ruoli);
        AuthUtente utenteSalvato = utenteRepository.save(utente);

        // Associazione automatica con Studente o Docente
        associateUserWithRole(signUpRequest, utenteSalvato, ruoli);

        return ResponseEntity.ok(new ResponseMessage("Utente registrato con successo!"));
    }

    // Metodo per assegnare i ruoli
    private Set<AuthRuolo> assignRoles(Set<String> strRuoli) {
        Set<AuthRuolo> ruoli = new HashSet<>();

        if (strRuoli == null || strRuoli.isEmpty()) {
            AuthRuolo defaultRuolo = ruoloRepository.findByNome(Ruolo.ROLE_STUDENTE)
                    .orElseThrow(() -> new RuntimeException("Errore: Ruolo predefinito non trovato."));
            ruoli.add(defaultRuolo);
        } else {
            strRuoli.forEach(r -> {
                switch (r.toLowerCase()) {
                    case "admin" -> ruoloRepository.findByNome(Ruolo.ROLE_ADMIN).ifPresent(ruoli::add);
                    case "docente" -> ruoloRepository.findByNome(Ruolo.ROLE_DOCENTE).ifPresent(ruoli::add);
                    default -> ruoloRepository.findByNome(Ruolo.ROLE_STUDENTE).ifPresent(ruoli::add);
                }
            });
        }

        return ruoli;
    }

    // Metodo per associare l'utente con il ruolo
    private void associateUserWithRole(Signup signUpRequest, AuthUtente utenteSalvato, Set<AuthRuolo> ruoli) {
        if (ruoli.stream().anyMatch(r -> r.getNome().equals(Ruolo.ROLE_STUDENTE))) {
            Studente studente = Studente.builder()
                    .nome(signUpRequest.getNome())
                    .cognome(signUpRequest.getCognome())
                    .email(signUpRequest.getEmail())
                    .dataNascita(signUpRequest.getDataNascita())
                    .authUtente(utenteSalvato)
                    .build();
            studenteRepository.save(studente);
        }

        if (ruoli.stream().anyMatch(r -> r.getNome().equals(Ruolo.ROLE_DOCENTE))) {
            Docente docente = Docente.builder()
                    .nome(signUpRequest.getNome())
                    .cognome(signUpRequest.getCognome())
                    .email(signUpRequest.getEmail())
                    .authUtente(utenteSalvato)
                    .build();
            docenteRepository.save(docente);
        }
    }
}
