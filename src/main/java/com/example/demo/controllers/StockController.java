package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StockDTO;
import com.example.demo.entities.Stock;
import com.example.demo.mapper.StockMapper;
import com.example.demo.services.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {
	@Autowired
	private StockService stockService;
	@Autowired
	private StockMapper stockMapper;

	
	@GetMapping
	public List<StockDTO> trouverTous(){
		return stockMapper.toListDto(stockService.trouverTous());
	}
	@GetMapping("/{id}")
	public StockDTO trouverParId(@PathVariable int id) {
		return stockMapper.toDto(stockService.trouverParId(id));
	}
	
	@GetMapping("/entrepot/{entrepotId}")
	public List<StockDTO> trouverParEntrepot(@PathVariable int entrepotId){
		return stockMapper.toListDto(stockService.trouverParEntrepot(entrepotId));
	}
	@GetMapping("/alertes")
	public List<StockDTO> trouverStockEnAlerte(){
		return stockMapper.toListDto(stockService.trouverStockEnAlerte());
	}
	@PostMapping("/produit/{produitId}/entrepot/{entrepotId}")
	public StockDTO ajouter(@PathVariable int produitId, @PathVariable int entrepotId, @RequestBody Stock s) {
	    return stockMapper.toDto(stockService.ajouter(produitId, entrepotId, s));
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> miseAJour(@PathVariable int id, @RequestBody Stock s){
		return stockService.miseAJour(id, s);
	}
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable int id ) {
		stockService.supprimer(id);
	}
}
