package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {
	List<Stock> findByEntrepotId(int entrepotId);
	
	@Query(value = "SELECT * FROM stock WHERE quantite <= seuil_alerte", nativeQuery = true)
	List<Stock> findStocksEnAlerte();
}
