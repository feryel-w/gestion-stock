package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EntrepotDTO {

		private int id;
		private String nom;
		private String adresse;
		private int capacite;
		private List<StockDTO> listeStock = new ArrayList<StockDTO>();
}
