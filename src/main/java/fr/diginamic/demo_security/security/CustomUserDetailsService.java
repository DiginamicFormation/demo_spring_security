package fr.diginamic.demo_security.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.diginamic.demo_security.entites.User;
import fr.diginamic.demo_security.repositories.UserRepository;

/**
 * Utilisée par AuthController pour authentifier l'utilisateur en 2 temps:
 * - il est appelé une 1ère fois indirectement par AuthenticationManager pour vérifier le user/password
 * - il est appelé une 2nds fois de manière directe pour récupérer le détail.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /** Pour l'accès en base de données */
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.isEnabled(),
            true, true, true,
            user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet())
        );
    }
}