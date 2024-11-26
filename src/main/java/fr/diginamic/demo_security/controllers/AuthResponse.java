package fr.diginamic.demo_security.controllers;

class AuthResponse {
	private String jwt;

	public AuthResponse(String jwt) {
		this.jwt = jwt;
	}

	/** Getter
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}

	/** Setter
	 * @param jwt the jwt to set
	 */
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
}