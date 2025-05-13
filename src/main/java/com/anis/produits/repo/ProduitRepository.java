package com.anis.produits.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.anis.produits.entities.Categorie;
import com.anis.produits.entities.Produit;

@RepositoryRestResource(path = "rest")
public interface ProduitRepository extends JpaRepository<Produit , Long>{
	/*Q8*/
	List<Produit> findByNomProduit(String nom);
	List<Produit> findByNomProduitContains(String nom);
	
	
	/*Q10*/
	@Query("select p from Produit p where p.nomProduit like %:nom and p.prixProduit > :prix")
	List<Produit> findByNomPrix (@Param("nom") String nom,@Param("prix") Double prix);
	
	//find by categories /* Q12 */
	
	@Query("select p from Produit p where p.categorie = ?1")
	List<Produit> findByCategorie (Categorie categorie);
	
	/* find by ID */
	
	List<Produit> findByCategorieIdCat(Long idCat);
/* Trier  Q16:17*/
	
	List<Produit> findByOrderByNomProduitAsc();
	/* Trier NOMPRIX  Q18	*/
	
	@Query("select p from Produit p order by p.nomProduit ASC, p.prixProduit DESC")
	List<Produit> trierProduitsNomsPrix ();
}			