package com.anis.produits.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.anis.produits.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long>
{
	
	
}