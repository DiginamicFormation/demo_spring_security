package fr.diginamic.demo_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configure la sécurité
 */
@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
	
	/** Filtre HTTP */
	@Autowired
	private JwtAuthenticationFilter jwtFilter;

	/** Met en place la sécurité
	 * @param http http security
	 * @return SecurityFilterChain 
	 * @throws Exception en cas de problème
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(AbstractHttpConfigurer::disable) // Désactiver CSRF
	        .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers("/api/auth/**").permitAll() // Autoriser /api/auth sans authentification
	            .anyRequest().authenticated() // Tous les autres endpoints sont authentifiés
	        );
	    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	}

    /** AuthenticationManager est utilisé pour vérifier le mot de passe de l'utilisateur.
     * Cet authenticationManager utilise {@link CustomUserDetailsService} par défaut
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /** Encrypteur utilisé pour le cryptage du mot de passe
     * @return {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}