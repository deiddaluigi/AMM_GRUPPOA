/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stampantiSrl.classi;


/**
 *
 * @author Luigi Deidda
 * 
 */
public class Cliente extends Account {
    private String nome, cognome;
    private String codiceFiscale;
    private TipoAccount account;
    
    /**
     *
     * @param cf
     */
    public Cliente(String cf){
        super();
        account = TipoAccount.CLIENTE;
        codiceFiscale = cf;
    }
    public void setNomeCognome(String nome, String cognome){
        this.nome = nome;
        this.cognome = cognome;
    }
    public String getCF(){
        return codiceFiscale;
    }
    public String getNome(){
        return nome;
    }
    public String getCognome(){
        return cognome;
    }
    @Override
    public String toString(){
        return getClass().getName() + 
                "[nome=" + nome + 
                "cognome=" + cognome +
                "codice fiscale=" + codiceFiscale + "]";
    }
    @Override
    public boolean equals(Object o){
        Cliente cliente = (Cliente) o;
        if (codiceFiscale != null && cliente.codiceFiscale != null
                && getClass().equals(cliente.getClass()))
            return codiceFiscale.equals(cliente.codiceFiscale);
        else return false;
    }
    @Override
    public int hashCode() {
        return codiceFiscale.hashCode();
    }  
}
