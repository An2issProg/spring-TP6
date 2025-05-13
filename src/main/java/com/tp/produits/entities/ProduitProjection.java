package com.tp.produits.entities;
import org.springframework.data.rest.core.config.Projection;

import com.anis.produits.entities.Produit;
@Projection(name = "nomProd", types = { Produit.class })
public interface ProduitProjection {
public String getNomProduit();
}