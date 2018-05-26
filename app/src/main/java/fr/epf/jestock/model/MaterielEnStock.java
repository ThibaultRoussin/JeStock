package fr.epf.jestock.model;


/**
 * Created by Thibault on 11/05/2018.
 */

public class MaterielEnStock {

    private String reference;
    private String nom;
    private int quantiteStock;
    private int quantiteStockConseillee;
    private int quantiteACommander;


    public MaterielEnStock() {
    }

    public MaterielEnStock(String reference, String nom, int quantiteStock, int quantiteStockConseillee, int quantiteACommander) {
        this.reference = reference;
        this.nom = nom;
        this.quantiteStock = quantiteStock;
        this.quantiteStockConseillee = quantiteStockConseillee;
        this.quantiteACommander = quantiteACommander;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public int getQuantiteStockConseillee() {
        return quantiteStockConseillee;
    }

    public void setQuantiteStockConseillee(int quantiteStockConseillee) {
        this.quantiteStockConseillee = quantiteStockConseillee;
    }

    public int getQuantiteACommander() {
        return quantiteACommander;
    }

    public void setQuantiteACommander(int quantiteACommander) {
        this.quantiteACommander = quantiteACommander;
    }
}
