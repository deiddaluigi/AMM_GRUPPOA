/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stampantiSrl.classi;

import java.util.ArrayList;

/**
 *
 * @author Luigi Deidda
 */
public class UtentiFactory {
    private static UtentiFactory singleton;
    // Lista Account
    private ArrayList<Account> listaAccount = new ArrayList<>();
    private String connectionString;
    
    public void setConnectionString(String s){
    this.connectionString = s;
    }
    
    public String getConnectionString(){
    return this.connectionString;
    }
    
    /**Costruisce alcune istanze di Clienti e una istanza Venditore in listaAccount, ed i rispettivi Conti Corrente*/
    private UtentiFactory(){
        Cliente cliente = new Cliente("BNCMRA81A01A100A");
        cliente.setNomeCognome("Mario", "Bianchi");
        cliente.setUsername("MarioBianchi");
        cliente.setPassword("aaabbb");
        listaAccount.add(cliente);
        cliente = new Cliente("MLNFRN82A02A200B");
        cliente.setNomeCognome("Franco", "Milani");
        cliente.setUsername("FrancoMilani");
        cliente.setPassword("cccddd");
        listaAccount.add(cliente);
        cliente = new Cliente("MRNVRN83A03A300C");
        cliente.setNomeCognome("Veronica", "Marini");
        cliente.setUsername("VeronicaMarini");
        cliente.setPassword("eeefff");
        listaAccount.add(cliente);
        Venditore venditore = new Venditore("StampantiSrl");
        venditore.setP_iva("01234567890");
        venditore.setUsername("stampantisrl");
        venditore.setPassword("ggghhh");
        listaAccount.add(venditore);
    }
    public static UtentiFactory getInstance() {
        if (singleton == null) {
            singleton = new UtentiFactory();
        }
        return singleton;
    }

    public ArrayList<Account> getAccountList(){
        return listaAccount;
    }
    public Account getAccount(int id){
        for(Account a : listaAccount){
            if(a.getId() == id)
                return a;
        }
        return null;
    }
}
