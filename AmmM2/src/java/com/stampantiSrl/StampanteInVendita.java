/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stampantiSrl;

/**
 *
 * @author Luigi Deidda
 */
public class StampanteInVendita {
    private int id;
    private static int progressivo_id = 0;
    private String marca, modello;
    private String urlImmagine;
    private String tipoStampa, gammaColori, altreCaratteristiche;
    private String descrizione;
    private double prezzoUnitario; // espresso in Euro
    private int quantita; //quantita' di pezzi disponibili

    public StampanteInVendita (String marca, String modello){
        progressivo_id++;
        id = progressivo_id;
        this.marca = marca;
        this.modello = modello;   
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modello
     */
    public String getModello() {
        return modello;
    }

    /**
     * @param modello the modello to set
     */
    public void setModello(String modello) {
        this.modello = modello;
    }

    /**
     * @return the urlImmagine
     */
    public String getUrlImmagine() {
        return urlImmagine;
    }

    /**
     * @param urlImmagine the urlImmagine to set
     */
    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    /**
     * @return the tipoStampa
     */
    public String getTipoStampa() {
        return tipoStampa;
    }

    /**
     * @param tipoStampa the tipoStampa to set
     */
    public void setTipoStampa(String tipoStampa) {
        this.tipoStampa = tipoStampa;
    }

    /**
     * @return the gammaColori
     */
    public String getGammaColori() {
        return gammaColori;
    }

    /**
     * @param gammaColori the gammaColori to set
     */
    public void setGammaColori(String gammaColori) {
        this.gammaColori = gammaColori;
    }

    /**
     * @return the altreCaratteristiche
     */
    public String getAltreCaratteristiche() {
        return altreCaratteristiche;
    }

    /**
     * @param altreCaratteristiche the altreCaratteristiche to set
     */
    public void setAltreCaratteristiche(String altreCaratteristiche) {
        this.altreCaratteristiche = altreCaratteristiche;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * @return the prezzoUnitario
     */
    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    /**
     * @param prezzoUnitario the prezzoUnitario to set
     */
    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    /**
     * @return the quantita
     */
    public int getQuantita() {
        return quantita;
    }

    /**
     * @param quantita the quantita to set
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
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

   
}