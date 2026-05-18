package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	Optional<Utilisateur> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	java.util.List<Utilisateur> findByRole(String role);

}
