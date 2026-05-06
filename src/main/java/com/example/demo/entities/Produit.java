package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(nullable = false)
	private String nom;
	
	@Column(nullable = false)
	private String categorie;
	
	
	@Column(nullable = false )
	private double prix; 
	
	@Column(nullable = false)
	private String fournisseur;
	
	@Column(nullable = false)
	private int seuilMin;
	
	@OneToMany(mappedBy = "produit")
	private List<Stock> listStock = new ArrayList<Stock>();
	
	@OneToMany(mappedBy =  "produit")
	private List<MouvementStock> listeMouvements = new ArrayList<MouvementStock>();
}
