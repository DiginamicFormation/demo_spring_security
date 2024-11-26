package fr.diginamic.demo_security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.demo_security.entites.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}