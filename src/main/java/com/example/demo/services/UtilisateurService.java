package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Utilisateur;
import com.example.demo.repositories.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class UtilisateurService {
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
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
	    if (utilisateurRepository.existsByEmail(u.getEmail())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email deja utilise");
	    }
	    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
	    if (!u.getMotDePasse().matches(regex)) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
	            "Le mot de passe doit contenir au moins une majuscule, une minuscule, un chiffre et un caractere special (@$!%*?&)");
	    }
	    u.setMotDePasse(passwordEncoder.encode(u.getMotDePasse()));
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
	    if (!passwordEncoder.matches(motDePasse, u.getMotDePasse())) {
	        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
	    }
	    return u;
	}
}
