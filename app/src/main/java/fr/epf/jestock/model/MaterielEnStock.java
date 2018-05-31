package fr.epf.jestock.model;


/**
 * Created by Thibault on 11/05/2018.
 */

public class MaterielEnStock{

    private boolean succes;
    private String nom;
    private long Reference;
    private int quantiteStock;
    private int quantiteStockConseillee;
    private int quantiteACommander;

    public MaterielEnStock() {

    }

    public MaterielEnStock(String nom, long reference, int quantiteStock, int quantiteStockConseillee, int quantiteACommander) {
        this.nom = nom;
        Reference = reference;
        this.quantiteStock = quantiteStock;
        this.quantiteStockConseillee = quantiteStockConseillee;
        this.quantiteACommander = quantiteACommander;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getReference() {
        return Reference;
    }

    public void setReference(long reference) {
        Reference = reference;
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
