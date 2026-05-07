package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MouvementStockDTO {
	private int id ; 
	private String type;
	private int quantite;
	private LocalDate date;
	private String nomProduit;
	private String nomEntrepot;
}
