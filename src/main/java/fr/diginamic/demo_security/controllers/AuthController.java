package fr.diginamic.demo_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.demo_security.security.CustomUserDetailsService;
import fr.diginamic.demo_security.security.JwtUtil;

/**
 * Contrôleur en charge de l'authentification
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /** authenticationManager : permet d'authentifier l'utilisateur */
    @Autowired
    private AuthenticationManager authenticationManager;

    /** Pour générer un token lors de l'authentification */
    @Autowired
    private JwtUtil jwtUtil;

    /** Permet d'aller cherche en base les infos utilisateur */
    @Autowired
    private CustomUserDetailsService userDetailsService;

    /** Endpoint de LOGIN qui reçoit un body contenant 2 infos : username et password (non crypté)
     * @param authRequest le body de la requête HTTP
     * @return {@link ResponseEntity}
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}