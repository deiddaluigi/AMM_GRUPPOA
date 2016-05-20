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
public class StampantiInVenditaFactory {
    
    // Lista Stampanti in Vendita
    private static ArrayList<StampanteInVendita> listaStampantiInVendita = new ArrayList<>();
    private ArrayList<String> altreCaratteristiche = new ArrayList<>();
    private String connectionString;
    
    //!da sostituire con il caricamento da database!
    
    public StampantiInVenditaFactory(){
        StampanteInVendita stampante = new StampanteInVendita("Canon","iP8750");
        stampante.setUrlImmagine("http://www.canon.it/Images/iP8750_Angle4_tcm80-1115696.jpg");
        stampante.setTipoStampa("inkjet");
        stampante.setGammaColori("colori");
        altreCaratteristiche.add("wireless");
        altreCaratteristiche.add("a3");
        stampante.setAltreCaratteristiche(altreCaratteristiche);
        stampante.setDescrizione("Risoluzione di stampa:\n" +
        "Fino a 9600 x 2400 dpi\n" +
        "Tecnologia di stampa:\n" +
        "6 inchiostri separati (PGBK, BK, C, M, Y, GY)\n" +
        "Sistema con testina di stampa FINE con gocce d'inchiostro da 1 pl (min.)\n" +
        "Inchiostri ChromaLife100+");
        stampante.setPrezzoUnitario(360);
        stampante.setQuantita(20);
        listaStampantiInVendita.add(stampante);
        stampante = new StampanteInVendita("Canon","i-SENSYS MF8550Cdn");
        stampante.setUrlImmagine("http://www.canon.it/Images/i-Sensys-MF8550Cdn_Default_tcm80-1035436.jpg");
        stampante.setTipoStampa("laser");
        stampante.setGammaColori("colori");
        altreCaratteristiche.add("multifunzione");
        altreCaratteristiche.add("wireless");
        altreCaratteristiche.add("fronte retro automatico");
        altreCaratteristiche = new ArrayList<>();
        stampante.setAltreCaratteristiche(altreCaratteristiche);
        stampante.setDescrizione("Risoluzione di stampa:\n" +
        "600 x 600 dpi\n");
        stampante.setPrezzoUnitario(420);
        stampante.setQuantita(4);
        listaStampantiInVendita.add(stampante);
    }
    
    //!creare metodo per nuova stampante!
    
    //!creare metodo per modificare stampante!
    
    //!creare metodo per eliminare stampante!
    
    
    private static StampantiInVenditaFactory singleton;
    public static StampantiInVenditaFactory getInstance() {
        if (singleton == null) {
            singleton = new StampantiInVenditaFactory();
        }
        return singleton;
    }
    public ArrayList<StampanteInVendita> getStampantiInVenditaList(){
        return listaStampantiInVendita;
    }
    public static StampanteInVendita getStampanteInVendita(int id){
        for(StampanteInVendita s : listaStampantiInVendita){
            if(s.getId() == id)
                return s;
        }
        return null;
    }
    public void setConnectionString(String s){
    this.connectionString = s;
    }   
    public String getConnectionString(){
    return this.connectionString;
    }
}
