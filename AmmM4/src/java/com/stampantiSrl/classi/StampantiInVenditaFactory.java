package com.stampantiSrl.classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Luigi Deidda
 */
public class StampantiInVenditaFactory {
    
    // Lista Stampanti in Vendita
    private static ArrayList<StampanteInVendita> listaStampantiInVendita = new ArrayList<>();
    private String connectionString;
    
    //!da sostituire con il caricamento da database!
    
    public StampantiInVenditaFactory(){
       /* StampanteInVendita stampante = new StampanteInVendita("Canon","iP8750");
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
        listaStampantiInVendita.add(stampante);*/
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
       try {
           listaStampantiInVendita.clear();
            try (Connection connessione = DriverManager.getConnection(connectionString, "stampantisrl", "aaabbb");Statement stmtStampanti = connessione.createStatement()) {
                String queryStampanti = "select * from stampanti_in_vendita";
                try (ResultSet resStampanti = stmtStampanti.executeQuery(queryStampanti)) {
                    StampanteInVendita stampante;
                    while (resStampanti.next()){
                        stampante = new StampanteInVendita(resStampanti.getString("marca"),resStampanti.getString("modello"));
                        stampante.setId(resStampanti.getInt("id"));
                        stampante.setUrlImmagine(resStampanti.getString("url_immagine"));
                        stampante.setTipoStampa(resStampanti.getString("tipo_stampa"));
                        stampante.setGammaColori(resStampanti.getString("gamma_colori"));
                        stampante.setDescrizione(resStampanti.getString("descrizione"));
                        stampante.setPrezzoUnitario(resStampanti.getDouble("prezzo_unitario"));
                        stampante.setQuantita(resStampanti.getInt("quantita"));
                        stampante.setVenditoreId(resStampanti.getInt("venditore_id"));
                        try (Statement stmtAltreCaratteristiche = connessione.createStatement()){
                            String queryAltreCaratteristiche = 
                                "SELECT altre_caratteristiche_stampante.CARATTERISTICA FROM altre_caratteristiche_stampante \n" +
                                "JOIN stampanti_altreCaratteristiche \n" +
                                "ON altre_caratteristiche_stampante.ID = " +
                                "stampanti_altreCaratteristiche.ALTRE_CARATTERISTICHE_STAMPANTE_ID\n" +
                                "where stampanti_altreCaratteristiche.STAMPANTI_IN_VENDITA_ID = " + stampante.getId();
                            try (ResultSet resAltreCaratteristiche = stmtAltreCaratteristiche.executeQuery(queryAltreCaratteristiche)){
                                ArrayList<String> listaAltreCaratteristiche = new ArrayList<>();
                                while (resAltreCaratteristiche.next()){
                                    listaAltreCaratteristiche.add(resAltreCaratteristiche.getString("caratteristica"));
                                }
                                stampante.setAltreCaratteristiche(listaAltreCaratteristiche);
                            }
                        }
                        listaStampantiInVendita.add(stampante);
                    }
                }    
            }
            return listaStampantiInVendita;
        } catch (SQLException e) {
            e.getCause();
            return null;          
        }      
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
