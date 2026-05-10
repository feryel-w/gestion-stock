package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Entrepot;
import com.example.demo.repositories.EntrepotRepository;

@Service
public class EntrepotService {
		@Autowired
		private EntrepotRepository entrepotRepository;

		public List<Entrepot> trouverTous() {
			
			return entrepotRepository.findAll();
			
			
		}
		
		
		public Entrepot trouverParId(int id) {
			return entrepotRepository.findById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrepot non trouvée"));
			
		}
		public Entrepot ajouter(Entrepot e) {
			return entrepotRepository.save(e);
		}
		
		public ResponseEntity<String> miseAJour(int id, Entrepot e){
			entrepotRepository.findById(id).ifPresentOrElse(
					ex -> { 
						ex.setNom(e.getNom());
						ex.setAdresse(e.getAdresse());
						ex.setCapacite(e.getCapacite());
						entrepotRepository.save(ex);
						
					}, 
					() -> {
						throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entrepot n'esxiste pas");
					});
			return ResponseEntity.ok("Entrepot mise a jour avec succes");
			
		
		}
		
		public void supprimer(int id) {
			if(!entrepotRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entrepot non trouvee ");
			}
			entrepotRepository.deleteById(id);
		}
		
}
