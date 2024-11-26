package fr.diginamic.demo_security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

	@GetMapping("/hello")
	@Secured("ADMIN")
	public ResponseEntity<?> getHello() {
		return ResponseEntity.ok("Hello");
	}
	
	@GetMapping("/hi")
	@Secured("USER")
	public ResponseEntity<?> getHi() {
		return ResponseEntity.ok("Hi");
	}
}