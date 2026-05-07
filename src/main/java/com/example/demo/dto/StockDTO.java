package com.example.demo.dto;

import lombok.Data;

@Data
public class StockDTO {
	private int id;
	private int quantite;
	private int seuilAlerte;
	private String nomProduit;
	private String nomEntrepot;
}
