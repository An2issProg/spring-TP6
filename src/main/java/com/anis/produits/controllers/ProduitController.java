package com.anis.produits.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anis.produits.entities.Categorie;
import com.anis.produits.entities.Produit;
import com.anis.produits.service.ProduitService;

import jakarta.validation.Valid;

@Controller
public class ProduitController {

    @Autowired
    private ProduitService produitService;


    @RequestMapping("/ListeProduits")
    public String listeProduits(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);

        return "listeProduits";
    }
/* on tp 6*/
   
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap)
    {
    List<Categorie> cats = produitService.getAllCategories();
    modelMap.addAttribute("produit", new Produit());
    modelMap.addAttribute("mode", "new");
    modelMap.addAttribute("categories", cats);
    return "formProduit";
    }

  /* Last update of saveProduit */
    @RequestMapping("/saveProduit")
    public String saveProduit(@Valid Produit produit, BindingResult bindingResult,
    @RequestParam (name="page",defaultValue = "0") int page,
    @RequestParam (name="size",defaultValue = "2") int size)
    {
    int currentPage;
    boolean isNew = false;
    if (bindingResult.hasErrors()) return "formProduit";
    if (produit.getIdProduit()==null) //ajout
    isNew=true;
    produitService.saveProduit(produit);
    if (isNew) //ajout
    {
    Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
    currentPage = prods.getTotalPages()-1;
    }
    else //modif
    currentPage=page;
    return ("redirect:/ListeProduits?page="+currentPage+"&size="+size);
    }
    

    // Suppression d’un produit
    @RequestMapping("/supprimerProduit")
    public String supprimerProduit(
            @RequestParam("id") Long id,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        produitService.deleteProduitById(id);
        Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);

        return "listeProduits";
    }

    // update TP6 modifier
    @RequestMapping("/modifierProduit")
    public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap)
    {
    Produit p= produitService.getProduit(id);
    List<Categorie> cats = produitService.getAllCategories();
    modelMap.addAttribute("produit", p);
    modelMap.addAttribute("mode", "edit");
    modelMap.addAttribute("categories", cats);
    return "formProduit";
    }

  
    @RequestMapping("/updateProduit")
    public String updateProduit(
            @ModelAttribute("produit") Produit produit,
            @RequestParam("date") String date,
            ModelMap modelMap) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateFormat.parse(date);
        produit.setDateCreation(dateCreation);

        produitService.updateProduit(produit);
        List<Produit> prods = produitService.getAllProduits();
        modelMap.addAttribute("produits", prods);

        return "listeProduits";
    }
}
