package fr.diginamic.demo_security.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Example: ROLE_USER, ROLE_ADMIN

	/** Getter
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/** Getter
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/** Setter
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}