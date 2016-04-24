/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stampantiSrl;

import java.util.ArrayList;

/**
 *
 * @author Luigi Deidda
 */
public class UtentiFactory {
    private static UtentiFactory singleton;
    // Lista Clienti
    private ArrayList<Cliente> listaClienti = new ArrayList<>();
    // Lista Venditori
    private ArrayList<Venditore> listaVenditori = new ArrayList<>();
    
    /**Costruisce alcune istanze di Clienti in listaClienti, una istanza Venditore, ed i rispettivi Conti Correnti*/
    private UtentiFactory(){
        Cliente cliente1 = new Cliente("BNCMRA81A01A100A");
        cliente1.setNomeCognome("Mario", "Bianchi");
        cliente1.setUsername("MarioBianchi");
        cliente1.setPassword("aaabbb");
        ContoCliente contoCliente1 = new ContoCliente(111, 1000);
        listaClienti.add(cliente1);
        Cliente cliente2 = new Cliente("MLNFRN82A02A200B");
        cliente1.setNomeCognome("Franco", "Milani");
        cliente1.setUsername("FrancoMilani");
        cliente1.setPassword("cccddd");
        ContoCliente contoCliente2 = new ContoCliente(222, 1200);
        listaClienti.add(cliente2);
        Cliente cliente3 = new Cliente("MRNVRN83A03A300C");
        cliente1.setNomeCognome("Veronica", "Marini");
        cliente1.setUsername("VeronicaMarini");
        cliente1.setPassword("eeefff");
        ContoCliente contoCliente3 = new ContoCliente(333, 1400);
        listaClienti.add(cliente3);
        Venditore venditore = new Venditore("StampantiSrl");
        venditore.setP_iva("01234567890");
        venditore.setUsername("MarioBianchi");
        venditore.setPassword("aaabbb");
        ContoVenditore contoVenditore = new ContoVenditore(555, 1000); 
        listaVenditori.add(venditore);
    }
    public static UtentiFactory getInstance() {
        if (singleton == null) {
            singleton = new UtentiFactory();
        }
        return singleton;
    }

    public ArrayList<Cliente> getClientiList()
    {
        return listaClienti;
    }
    public Cliente getCliente(int id)
    {
        for(Cliente c : listaClienti)
        {
            if(c.getId() == id)
                return c;
        }
        return null;
    }
    public ArrayList<Venditore> getVenditoriList()
    {
        return listaVenditori;
    }
    public Venditore getVenditore(int id)
    {
        for(Venditore v : listaVenditori)
        {
            if(v.getId() == id)
                return v;
        }
        return null;
    }
}
