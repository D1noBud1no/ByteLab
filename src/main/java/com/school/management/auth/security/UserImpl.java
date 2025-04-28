package com.school.management.auth.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.management.auth.authmodel.AuthUtente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Implementazione di UserDetails per gestire i dettagli dell'utente autenticato.
 */
@AllArgsConstructor
@Getter
public class UserImpl implements UserDetails {

    private Long id;
    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Costruisce un'istanza di UserImpl a partire da un oggetto AuthUtente.
     *
     * @param utente L'oggetto AuthUtente da cui costruire UserImpl.
     * @return Un'istanza di UserImpl.
     */
    public static UserImpl build(AuthUtente utente) {
        List<GrantedAuthority> authorities = utente.getRuoli().stream()
                .map(ruolo -> (GrantedAuthority) () -> ruolo.getNome().name())
                .toList();

        return new UserImpl(
                utente.getId(),
                utente.getUsername(),
                utente.getPassword(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserImpl that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
