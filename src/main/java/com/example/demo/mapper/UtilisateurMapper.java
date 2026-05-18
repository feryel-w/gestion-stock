package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UtilisateurDTO;
import com.example.demo.entities.Utilisateur;

@Component
public class UtilisateurMapper {
	
	private ModelMapper mmapper;
	
	public UtilisateurDTO toDto(Utilisateur u ) {
		return mmapper.map(u, UtilisateurDTO.class);
	}
	public Utilisateur fromDto(UtilisateurDTO uDto) {
		return mmapper.map(uDto, Utilisateur.class);
		
	}
	
	public List<UtilisateurDTO> toListDto(List<Utilisateur> listeUtilisateurs){
		return listeUtilisateurs.stream()
				.map(u -> mmapper.map(u, UtilisateurDTO.class))
				.collect(Collectors.toList());
	}
	

}
