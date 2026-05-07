package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.EntrepotDTO;
import com.example.demo.entities.Entrepot;

@Component
public class EntrepotMapper {

	@Autowired
	private ModelMapper mmapper;
	
	private StockMapper stockMapper;
	
	public EntrepotDTO toDto(Entrepot e) {
		EntrepotDTO eDto = mmapper.map(e, EntrepotDTO.class);
		eDto.setListeStock(stockMapper.toListDto(e.getListeStock()));
		return eDto;
		
	}
	
	public Entrepot fromDto(EntrepotDTO eDto) {
		return mmapper.map(eDto, Entrepot.class);
	}
	
	public List<EntrepotDTO> toListDto(List<Entrepot> listeEntrepots){
		return listeEntrepots.stream()
				.map(e -> toDto(e))
				.collect(Collectors.toList());
	}
}
