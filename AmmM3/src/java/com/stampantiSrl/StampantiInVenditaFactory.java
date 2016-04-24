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
public class StampantiInVenditaFactory {
    
    // Lista Stampanti in Vendita
    private ArrayList<StampanteInVendita> listaStampantiInVendita = new ArrayList<>();
    
    public StampantiInVenditaFactory(){
        StampanteInVendita stampante = new StampanteInVendita("Canon","iP8750");
        stampante.setTipoStampa("http://www.canon.it/Images/iP8750_Angle4_tcm80-1115696.jpg");
        stampante.setTipoStampa("inkjet");
        stampante.setGammaColori("colori");
        stampante.setMultifunzione(false);
        stampante.setWireless(true);
        stampante.setA3(true);
        stampante.setFronteRetro(false);
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
        stampante.setTipoStampa("http://www.canon.it/Images/i-Sensys-MF8550Cdn_Default_tcm80-1035436.jpg");
        stampante.setTipoStampa("laser");
        stampante.setGammaColori("colori");
        stampante.setMultifunzione(true);
        stampante.setWireless(true);
        stampante.setA3(false);
        stampante.setFronteRetro(true);
        stampante.setDescrizione("Risoluzione di stampa:\n" +
        "600 x 600 dpi\n");
        stampante.setPrezzoUnitario(420);
        stampante.setQuantita(4);
        listaStampantiInVendita.add(stampante);
        
    }
    
    private static StampantiInVenditaFactory singleton;
    public static StampantiInVenditaFactory getInstance() {
        if (singleton == null) {
            singleton = new StampantiInVenditaFactory();
        }
        return singleton;
    }
}
