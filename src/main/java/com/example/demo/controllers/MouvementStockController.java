package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MouvementStockDTO;
import com.example.demo.mapper.MouvementStockMapper;
import com.example.demo.services.MouvementStockService;

@RestController
@RequestMapping("/mouvements")
public class MouvementStockController {

	@Autowired
	private MouvementStockService mouvementService;
	@Autowired
	private MouvementStockMapper mouvemenMapper;
	
	
	@GetMapping
	public List<MouvementStockDTO> trouverTous(){
		return mouvemenMapper.toListDto(mouvementService.trouverTous());
	}
	@GetMapping("/entrepot/{entrepotId}")
	public List<MouvementStockDTO> trouverParEntrepot(@PathVariable int entrepotId){
		return mouvemenMapper.toListDto(mouvementService.trouverParEntrepot(entrepotId));
	}
	
	@GetMapping("/produit/{produitId}")
	public List<MouvementStockDTO> trouverParProduit(@PathVariable int produitId){
		return mouvemenMapper.toListDto(mouvementService.trouverParProduit(produitId));
	}
	
	@PostMapping
	public MouvementStockDTO enregistrerMouvement(
	        @RequestParam int produitId,
	        @RequestParam int entrepotId,
	        @RequestParam String type,
	        @RequestParam int quantite) {
	    return mouvemenMapper.toDto(mouvementService.enregistrerMouvement(produitId, entrepotId, type, quantite));
	}
			
}
