package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Produit;
import com.example.demo.repositories.ProduitRepository;

@Service
public class ProduitService {

	
	@Autowired
	private ProduitRepository produitRepository;
	
	
	public List<Produit> trouverTous(){
		return produitRepository.findAll();
	}
	
	public Produit trouverParId(int id) {
		return produitRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "produit non trouvée"));
	}
	
	public List<Produit> trouverParCategorie(String categorie){
		return produitRepository.findByCategorie(categorie);
	}
	
	public List<Produit> trouverParFournisseur(String fournisseur){
		return produitRepository.findByFournisseur(fournisseur);
	}
	public Produit ajouter(Produit p) {
		return produitRepository.save(p);
	}
	public ResponseEntity<String> miseAJour(int id, Produit p){
		produitRepository.findById(id).ifPresentOrElse(
				ex -> {
					ex.setNom(p.getNom());
					ex.setCategorie(p.getCategorie());
					ex.setPrix(p.getPrix());
					ex.setFournisseur(p.getFournisseur());
					ex.setSeuilMin(p.getSeuilMin());
					produitRepository.save(ex);
					
				},
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit n'existe pas");
				}
				
				);
		return ResponseEntity.ok("Produit mis a jour avec success");
	}
	public void supprimer(int id ) {
		if(!produitRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvee");
			
		}
		produitRepository.deleteById(id);
	}
}
