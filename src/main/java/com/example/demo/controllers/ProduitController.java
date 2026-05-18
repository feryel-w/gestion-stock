package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entities.Produit;
import com.example.demo.mapper.ProduitMapper;
import com.example.demo.services.ProduitService;


@RestController
@RequestMapping("/produits")
public class ProduitController {
	@Autowired
	private ProduitService produitService;
	@Autowired
	private ProduitMapper produitMapper;
	
	@GetMapping
	public List<ProduitDTO> trouverTous(){
		return produitMapper.toListDto(produitService.trouverTous());
	}
	@GetMapping("/{id}")
	public ProduitDTO trouverParId(@PathVariable int id) {
		return produitMapper.toDto(produitService.trouverParId(id));
	}
	@GetMapping("/categorie/{categorie}")
	public List<ProduitDTO> trouverParCategorie(@PathVariable String categorie){
		return produitMapper.toListDto(produitService.trouverParCategorie(categorie));
	}
	@GetMapping("/fournisseur/{fournisseur}")
	public List<ProduitDTO> trouverParFournisseur(@PathVariable String fournisseur){
		return produitMapper.toListDto(produitService.trouverParFournisseur(fournisseur));
	}
	@PostMapping
	public ProduitDTO ajouter(@RequestBody Produit p) {
	    return produitMapper.toDto(produitService.ajouter(p));
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> miseAJour(@PathVariable int id, @RequestBody Produit p){
		return produitService.miseAJour(id, p);
	}
	
	public void supprimer(@PathVariable int id) {
		produitService.supprimer(id);
	}
	
}
