package com.example.demo.mapper;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.StockDTO;
import com.example.demo.entities.Stock;

@Component
public class StockMapper {
	@Autowired
	private ModelMapper mmapper;
	
	public StockDTO toDto(Stock s) {
		StockDTO sDto = mmapper.map(s, StockDTO.class);
		sDto.setNomProduit(s.getProduit().getNom());
		sDto.setNomEntrepot(s.getEntrepot().getNom());
		return sDto;
	}
	
	
	
	public Stock fromDto(StockDTO sDto) {
		return mmapper.map(sDto, Stock.class);
		
	}
	
	
	
	public List<StockDTO> toListDto(List<Stock> listeStock) {
	    if (listeStock == null) return new ArrayList<>();
	    return listeStock.stream()
	            .map(s -> toDto(s))
	            .collect(Collectors.toList());
	}
}
