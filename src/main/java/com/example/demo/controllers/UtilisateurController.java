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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UtilisateurDTO;
import com.example.demo.entities.Utilisateur;
import com.example.demo.mapper.UtilisateurMapper;
import com.example.demo.services.UtilisateurService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private UtilisateurMapper utilisateurMapper;
	
	@GetMapping
	public List<UtilisateurDTO> trouverTous(){
		return utilisateurMapper.toListDto(utilisateurService.trouverTous());
	}
	
	@GetMapping("/{id}")
	public UtilisateurDTO trouverParId(@PathVariable int id) {
		return utilisateurMapper.toDto(utilisateurService.trouverParId(id));
	}
	@GetMapping("/role/{role}")
	public List<UtilisateurDTO> trouverParRole(@PathVariable String role){
		return utilisateurMapper.toListDto(utilisateurService.trouverParRole(role));
	}
	
	@PostMapping("/inscrire")
	public UtilisateurDTO inscrire(@Valid @RequestBody Utilisateur u) {
		return utilisateurMapper.toDto(utilisateurService.inscrire(u));
	}
	@PostMapping("/connecter")	
	public UtilisateurDTO connecter(@RequestParam String email, @RequestParam String motDePasse) {
		return utilisateurMapper.toDto(utilisateurService.connecter(email, motDePasse));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> miseAJour(@PathVariable int id, @RequestBody Utilisateur u){
		return utilisateurService.miseAJour(id, u);
	}

	
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable int id) {
		utilisateurService.supprimer(id);
	}
	
}
