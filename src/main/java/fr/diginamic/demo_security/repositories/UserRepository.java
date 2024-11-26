package fr.diginamic.demo_security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.demo_security.entites.User;

/**
 * Accès base de données
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
	/** Recherche un User via son username
     * @param username username
     * @return {@link Optional}
     */
    Optional<User> findByUsername(String username);
}