package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Entrepot {
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nom;
	
	@Column(nullable = false)
	private String adresse;
	@Column(nullable = false)
	private int capacite;
	
	@OneToMany(mappedBy = "entrepot")
	private List<Stock> listeStock = new ArrayList<Stock>();
	

}
