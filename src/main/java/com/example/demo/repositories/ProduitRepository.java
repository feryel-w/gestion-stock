package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {

	
	
		List<Produit> findByCategorie(String categorie);
		List<Produit> findByFournisseur(String fournisseur);
}
