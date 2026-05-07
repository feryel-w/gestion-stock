package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.MouvementStockDTO;
import com.example.demo.entities.MouvementStock;

@Component
public class MouvementStockMapper {
	@Autowired 
	private ModelMapper mmapper;
	
	public MouvementStockDTO toDto(MouvementStock m) {
		MouvementStockDTO mDto = mmapper.map(m, MouvementStockDTO.class);
		mDto.setNomProduit(m.getProduit().getNom());
		mDto.setNomEntrepot(m.getEntrepot().getNom());
		return mDto;
		
		
	}
	
	public MouvementStock fromDto(MouvementStockDTO mDto) {
		return mmapper.map(mDto, MouvementStock.class);
	}
	public List<MouvementStockDTO> toListDto(List<MouvementStock> listeMouvements){
		return listeMouvements.stream()
				.map(m -> toDto(m))
				.collect(Collectors.toList());
	}



}
