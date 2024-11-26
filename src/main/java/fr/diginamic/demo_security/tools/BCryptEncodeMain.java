package fr.diginamic.demo_security.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncodeMain {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("a"));

	}

}
