package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.MouvementStock;
import com.example.demo.entities.Stock;
import com.example.demo.repositories.EntrepotRepository;
import com.example.demo.repositories.MouvementStockRepository;
import com.example.demo.repositories.ProduitRepository;
import com.example.demo.repositories.StockRepository;

@Service
public class MouvementStockService {
	
	@Autowired
	private MouvementStockRepository mouvementRepository;
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private ProduitRepository produiRepository;
	
	@Autowired
	private EntrepotRepository entrepotRepository;
	
	
	public List<MouvementStock> trouverTous(){
		return mouvementRepository.findAll();
	}
	
	public List<MouvementStock> trouverParEntrepot(int entrepotId){
		return mouvementRepository.findByEntrepotId(entrepotId);
	}
	
	public List<MouvementStock> trouverParProduit(int produiId){
		return mouvementRepository.findByProduitId(produiId);
	}
	
	public MouvementStock enregistrerMouvement(int produitId, int entrepotId, String type, int quantite) {
		if(!type.equals("entree") && !type.equals("sortie")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Type doit etre entree ou sortie");
		}
		
		Stock stock = stockRepository.findByEntrepotId(entrepotId).stream()
				.filter(s -> s.getProduit().getId() == produitId)
				.findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock non trouvee pour ce produit et entrepot"));
		
		if (type.equals("entree")) {
			stock.setQuantite(stock.getQuantite() + quantite);
		} else {
			if (stock.getQuantite() < quantite) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantite insuffisante en stock ");
			}
			stock.setQuantite(stock.getQuantite() - quantite);
		}
		stockRepository.save(stock);
		
		MouvementStock m = new MouvementStock();
		m.setType(type);
		m.setQuantite(quantite);
		m.setDate(LocalDate.now());
		m.setProduit(produiRepository.findById(produitId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvee")));
		m.setEntrepot(entrepotRepository.findById(entrepotId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrpot non trouvee")));
		
		return mouvementRepository.save(m);
	}

}
