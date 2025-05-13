package com.anis.produits.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.anis.produits.entities.Categorie;
import com.anis.produits.entities.Produit;


public interface ProduitService {
	
	Produit saveProduit(Produit p);
	Produit updateProduit(Produit p);
	void deleteProduit(Produit p);
	void deleteProduitById(Long id);	
	Produit getProduit(Long id);
	List<Produit> getAllProduits();
	
	/*PARTIE DE LA TP4 */ 
	List<Produit> findByNomProduit(String nom);
	List<Produit> findByNomProduitContains(String nom);
	List<Produit> findByNomPrix (String nom, Double prix);
	List<Produit> findByCategorie (Categorie categorie);
	List<Produit> findByCategorieIdCat(Long id);
	List<Produit> findByOrderByNomProduitAsc();
	List<Produit> trierProduitsNomsPrix();
	
	/*TP6*/
	List<Categorie> getAllCategories();
	
	

	//ADDED CODE Q4- TP3
    Page<Produit> getAllProduitsParPage(int page, int size);

}
