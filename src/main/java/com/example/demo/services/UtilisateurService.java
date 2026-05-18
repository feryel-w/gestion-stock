package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Utilisateur;
import com.example.demo.repositories.UtilisateurRepository;

@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public List<Utilisateur> trouverTous(){
		return utilisateurRepository.findAll();
	}
	public Utilisateur trouverParId(int id) {
		return utilisateurRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvee"));
	}
	public List<Utilisateur> trouverParRole(String role){
		return utilisateurRepository.findByRole(role);
		
	}
	
	public Utilisateur inscrire(Utilisateur u) {
		if(utilisateurRepository.existsByEmail(u.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email deja utilisee");
		}
		return utilisateurRepository.save(u);
	}
	
	public ResponseEntity<String> miseAJour(int id, Utilisateur u){
		utilisateurRepository.findById(id).ifPresentOrElse(
				ex -> {
					ex.setNom(u.getNom());
					ex.setEmail(u.getEmail());
					ex.setRole(u.getRole());
					utilisateurRepository.save(ex);
				},
				() ->{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur n existe pas");
				});
		return ResponseEntity.ok("Utilisateur mis a jour avec success");
				
	}
	
	public void supprimer(int id) {
		if(!utilisateurRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvee");
		}
		utilisateurRepository.deleteById(id);
	}
	
	public Utilisateur connecter(String email, String motDePasse) {
	    Utilisateur u = utilisateurRepository.findByEmail(email)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email incorrect"));
	    if (!u.getMotDePasse().equals(motDePasse)) {
	        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
	    }
	    return u;
	}
}
