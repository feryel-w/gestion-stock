package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private int quantite;
	
	
	@Column(nullable = false)
	private int seuilAlerte;
	
	
	@ManyToOne
	@JoinColumn(name = "produit_id")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name ="entrepot_id")
	private Entrepot entrepot;

}
