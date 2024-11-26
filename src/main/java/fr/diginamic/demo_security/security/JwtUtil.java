package fr.diginamic.demo_security.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Fournit des services de génération de token JWT et de vérification
 */
@Component
public class JwtUtil {

	/** secretKey */
	@Value("${jwt.secret}")
	private String secretKey;

	/** expireIn */
	@Value("${jwt.expires_in}")
	private long expireIn;

	/**
	 * Génère un token JWT Contenant
	 * 
	 * @param username nom de l'utilisateur
	 * @return String
	 */
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * expireIn))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256).compact();
	}

	/** Extrait le username du token
	 * @param token token JWT
	 * @return String
	 */
	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build()
				.parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Retourne si oui ou non le token JWT est valide. Il doit contenir le nom de
	 * l'utilisateur authentifié et ne doit pas avoir expiré.
	 * 
	 * @param token token
	 * @param userDetails infos concernant l'utilisateur authentifié
	 * @return boolean
	 */
	public boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	/**
	 * Contrôle si le JWT token a expiré ou non
	 * 
	 * @param token token JWT
	 * @return boolean
	 */
	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build()
				.parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}
}