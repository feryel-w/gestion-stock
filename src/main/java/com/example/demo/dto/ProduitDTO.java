package com.example.demo.dto;

import lombok.Data;
@Data	

public class ProduitDTO {
	
	private int id;
	private String nom;
	private String categorie;
	private double prix;
	private String fournisseur;
	private int seuilMin;

}
