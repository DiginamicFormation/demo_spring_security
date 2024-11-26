package fr.diginamic.demo_security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur de démonstration : permet de montrer 2 endpoints sécurisés en
 * fonction d'un rôle utilisateur
 */
@RestController
@RequestMapping("/api")
public class HelloController {

	/** Endpoint sécurisé pour les profils ADMIN
	 * @return {@link ResponseEntity}
	 */
	@GetMapping("/hello")
	@Secured("ADMIN")
	public ResponseEntity<?> getHello() {
		return ResponseEntity.ok("Hello");
	}

	/** Endpoint sécurisé pour les profils USER
	 * @return {@link ResponseEntity}
	 */
	@GetMapping("/hi")
	@Secured("USER")
	public ResponseEntity<?> getHi() {
		return ResponseEntity.ok("Hi");
	}
}