package com.example.demo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Stock;
import com.example.demo.repositories.EntrepotRepository;
import com.example.demo.repositories.ProduitRepository;
import com.example.demo.repositories.StockRepository;

@Service
public class StockService {



	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private ProduitRepository produitRepository;

	
	@Autowired
	private EntrepotRepository entrepotRepository;



	public List<Stock> trouverTous(){
		return stockRepository.findAll();
	}
	
	public Stock trouverParId(int id) {
		return stockRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Stock non trouvee "));
	}

	
	public List<Stock> trouverStockEnAlerte(){
		return stockRepository.findStocksEnAlerte();
		
	}
	public Stock ajouter(int produitId, int entrepotId, Stock s) {
		s.setProduit(produitRepository.findById(produitId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvee")));
		s.setEntrepot(entrepotRepository.findById(entrepotId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrepot non trouvee")));
		return stockRepository.save(s);
				
	}
	
	public ResponseEntity<String> miseAJour (int id, Stock s){
		stockRepository.findById(id).ifPresentOrElse(
				ex -> {
					ex.setQuantite(s.getQuantite());
					ex.setSeuilAlerte(s.getSeuilAlerte());
					stockRepository.save(ex);
				},
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock n'existe pas");
				});
		return ResponseEntity.ok("Stock mis a jour avec succes");
	}
	public void supprimer(int id) {
		if (!stockRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock non trouvee");
		}
		stockRepository.deleteById(id);
	}
}
