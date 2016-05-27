package com.stampantiSrl.classi;

import java.util.ArrayList;

/**
 *
 * @author Luigi Deidda
 */
public class StampanteInVendita implements Cloneable{
    private int id;
    private String marca, modello;
    private String urlImmagine;
    private String tipoStampa, gammaColori;
    private ArrayList<String> altreCaratteristiche;
    private String descrizione;
    private double prezzoUnitario; // espresso in Euro
    private int quantita; //quantita' di pezzi disponibili o da acquistare
    private int venditoreId;
    
    public StampanteInVendita (String marca, String modello){
        this.marca = marca;
        this.modello = modello; 
    }
    
    /**
     * @return id
     */
    public int getId() {
        return id;
    }
    
    /**
     * @param id  id da impostare
     */
    
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca  marca da impostare
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return modello
     */
    public String getModello() {
        return modello;
    }

    /**
     * @param modello modello da impostare
     */
    public void setModello(String modello) {
        this.modello = modello;
    }

    /**
     * @return urlImmagine
     */
    public String getUrlImmagine() {
        return urlImmagine;
    }

    /**
     * @param urlImmagine urlImmagine da impostare
     */
    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    /**
     * @return tipoStampa
     */
    public String getTipoStampa() {
        return tipoStampa;
    }

    /**
     * @param tipoStampa tipoStampa da impostare
     */
    public void setTipoStampa(String tipoStampa) {
        this.tipoStampa = tipoStampa;
    }

    /**
     * @return gammaColori
     */
    public String getGammaColori() {
        return gammaColori;
    }

    /**
     * @param gammaColori gammaColori da impostare
     */
    public void setGammaColori(String gammaColori) {
        this.gammaColori = gammaColori;
    }

   /**
     * @return altreCaratteristiche
     */
    public ArrayList<String> getAltreCaratteristiche() {
        return altreCaratteristiche;
    }

    /**
     * @param altreCaratteristiche altreCaratteristiche da impostare
     */
    public void setAltreCaratteristiche(ArrayList<String> altreCaratteristiche) {
        this.altreCaratteristiche = altreCaratteristiche;
    }
 
    /**
     * @return descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione descrizione da impostare
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * @return prezzoUnitario
     */
    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    /**
     * @param prezzoUnitario  prezzoUnitario da impostare
     */
    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    /**
     * @return quantita
     */
    public int getQuantita() {
        return quantita;
    }

    /**
     * @param quantita quantita da impostare
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    
    /**
     * @return id venditore
     */
    
    public int getVenditoreId() {
        return venditoreId;
    }
     /**
     * @param venditoreId id venditore da impostare
     */
    public void setVenditoreId(int venditoreId) {
        this.venditoreId = venditoreId;
    }
    
    @Override
    public boolean equals(Object o){
        StampanteInVendita stampante = (StampanteInVendita) o;
        if (modello != null && stampante.modello != null
                && getClass().equals(stampante.getClass()))
            return modello.equals(stampante.modello);
        else return false;
    }
    @Override
    public int hashCode() {
        return modello.hashCode();
    }  
    @Override
    public Object clone() throws CloneNotSupportedException{
        try{
            Object clonedStampante = super.clone();
            return clonedStampante;
        } catch(CloneNotSupportedException e) {
            return null;
        }  
    }  
}