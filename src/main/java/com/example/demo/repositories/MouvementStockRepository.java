package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.MouvementStock;

public interface MouvementStockRepository extends JpaRepository<MouvementStock,Integer> {
	
	List<MouvementStock> findByEntrepotID(int entrepotId);
	
	List<MouvementStock> findByProduitId(int produitId);
}
