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

import com.example.demo.dto.EntrepotDTO;
import com.example.demo.entities.Entrepot;
import com.example.demo.mapper.EntrepotMapper;
import com.example.demo.services.EntrepotService;

@RestController
@RequestMapping("/entrepots")
public class EntrepotController {
	
	@Autowired
	private EntrepotService entrepotService;
	@Autowired
	private EntrepotMapper entrepotMapper;
	
	@GetMapping
	public List<EntrepotDTO> trouverTous(){
		return entrepotMapper.toListDto(entrepotService.trouverTous());
	}
	
	@PostMapping
	public Entrepot ajouter(@RequestBody Entrepot e) {
		return entrepotService.ajouter(e);
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> miseAJour(@PathVariable int id, @RequestBody Entrepot e){
		return entrepotService.miseAJour(id, e);
	}
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable int id) {
		entrepotService.supprimer(id);
	}
	
}
