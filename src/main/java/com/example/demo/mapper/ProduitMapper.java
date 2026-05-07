package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entities.Produit;

@Component
public class ProduitMapper {
	
	
	@Autowired
	private ModelMapper mmapper;
	
	public ProduitDTO toDto(Produit p) {
		ProduitDTO pDto = mmapper.map(p,ProduitDTO.class);
		return pDto;
	}
	
	public Produit fromDto(ProduitDTO pDto) {
		return mmapper.map(pDto, Produit.class);
	}
	
	public List<ProduitDTO> toListDto(List<Produit> listeProduits){
		return listeProduits.stream()
				.map(p -> mmapper.map(p, ProduitDTO.class))
				.collect(Collectors.toList());
	}
}
