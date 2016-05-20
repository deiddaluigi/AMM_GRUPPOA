/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stampantiSrl.classi;


/**
 *
 * @author Luigi Deidda
 */
public class Venditore extends Account{
    private String ragioneSociale;
    private String p_iva;
    
    public Venditore(String ragioneSociale){
        super();
        this.ragioneSociale = ragioneSociale;
    }
    public void setP_iva(String p_iva){
        this.p_iva = p_iva;
    }
     public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }
    public String getRagioneSociale(){
        return ragioneSociale;
    }
    public String getP_iva(){
        return p_iva;
    }
    @Override
    public String toString(){
        return getClass().getName() + 
                "[ragione sociale=" + ragioneSociale + 
                "partita iva=" + p_iva + "]";
    }
    @Override
    public boolean equals(Object o){
        Venditore venditore = (Venditore) o;
        if (ragioneSociale != null && venditore.ragioneSociale != null
                && getClass().equals(venditore.getClass()))
            return ragioneSociale.equals(venditore.ragioneSociale);
        else return false;
    }
    @Override
    public int hashCode() {
        return ragioneSociale.hashCode();
    }  
}
