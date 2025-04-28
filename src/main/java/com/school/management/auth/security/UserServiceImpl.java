package com.school.management.auth.security;

import com.school.management.auth.authmodel.AuthUtente;
import com.school.management.auth.authrepository.AuthUtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementazione del servizio UserDetailsService per caricare i dettagli dell'utente.
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUtenteRepository utenteRepository;

    /**
     * Carica un utente dal database tramite il nome utente.
     *
     * @param username Il nome utente dell'utente da caricare.
     * @return Un'istanza di UserDetails contenente i dettagli dell'utente.
     * @throws UsernameNotFoundException Se l'utente non viene trovato.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUtente utente = utenteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + username));

        return UserImpl.build(utente);
    }
}
