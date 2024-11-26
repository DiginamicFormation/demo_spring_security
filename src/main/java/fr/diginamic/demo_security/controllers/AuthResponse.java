package fr.diginamic.demo_security.controllers;
/**
 * Sert à retourner le token JWT dans le body de la réponse
 */
class AuthResponse {
	
    /** token jwt */
    private String jwt;
    
    /** Constructeur
     * @param jwt valeur du token JWT
     */
    public AuthResponse(String jwt) { 
    	this.jwt = jwt; 
    }

	/** Getter
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}
    
}