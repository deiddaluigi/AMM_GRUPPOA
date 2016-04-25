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
    
    /**Costruisce alcune istanze di Clienti e una istanza Venditore in listaAccount, ed i rispettivi Conti Corrente*/
    private UtentiFactory(){
        Cliente cliente1 = new Cliente("BNCMRA81A01A100A");
        cliente1.setNomeCognome("Mario", "Bianchi");
        cliente1.setUsername("MarioBianchi");
        cliente1.setPassword("aaabbb");
        ContoCliente contoCliente1 = new ContoCliente(111, 1000);
        listaAccount.add(cliente1);
        Cliente cliente2 = new Cliente("MLNFRN82A02A200B");
        cliente2.setNomeCognome("Franco", "Milani");
        cliente2.setUsername("FrancoMilani");
        cliente2.setPassword("cccddd");
        ContoCliente contoCliente2 = new ContoCliente(222, 1200);
        listaAccount.add(cliente2);
        Cliente cliente3 = new Cliente("MRNVRN83A03A300C");
        cliente3.setNomeCognome("Veronica", "Marini");
        cliente3.setUsername("VeronicaMarini");
        cliente3.setPassword("eeefff");
        ContoCliente contoCliente3 = new ContoCliente(333, 1400);
        listaAccount.add(cliente3);
        Venditore venditore = new Venditore("StampantiSrl");
        venditore.setP_iva("01234567890");
        venditore.setUsername("stampantisrl");
        venditore.setPassword("ggghhh");
        ContoVenditore contoVenditore = new ContoVenditore(555, 1000); 
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
