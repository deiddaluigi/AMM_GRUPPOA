package com.stampantiSrl.classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    
    private StampantiInVenditaFactory(){   
    }
    
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
            try (Connection connessione = DriverManager.getConnection(connectionString, "stampantisrldb", "aaabbb");
                Statement stmtStampanti = connessione.createStatement()) {
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
                                "SELECT altre_caratteristiche_stampante.CARATTERISTICA FROM altre_caratteristiche_stampante " +
                                "JOIN stampanti_altreCaratteristiche " +
                                "ON altre_caratteristiche_stampante.ID = " +
                                "stampanti_altreCaratteristiche.ALTRE_CARATTERISTICHE_STAMPANTE_ID " +
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
    public ArrayList<StampanteInVendita> getStampantiVenditoreList(int idVenditore){
        ArrayList<StampanteInVendita> listaStampantiVenditore = new ArrayList<>();
        for(StampanteInVendita s : this.getStampantiInVenditaList()){
            if(s.getVenditoreId() == idVenditore){
                listaStampantiVenditore.add(s);
            }    
        }
        return listaStampantiVenditore;
    }
    
    public static StampanteInVendita getStampanteInVendita(int id){
        for(StampanteInVendita s : listaStampantiInVendita){
            if(s.getId() == id)
                return s;
        }
        return null;
    }
    
    public boolean addStampanteInVendita(StampanteInVendita nuova){
            try (Connection connessione = DriverManager.getConnection(connectionString, "stampantisrldb", "aaabbb")) {
                int id = -1;
                String queryAddStampante = 
                    "INSERT INTO stampanti_in_vendita(id, marca, modello, url_immagine, " +
                    "tipo_stampa, gamma_colori, descrizione, prezzo_unitario, quantita, venditore_id)" +
                    "VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmtAddStampante = connessione.prepareStatement(queryAddStampante, Statement.RETURN_GENERATED_KEYS)) {
                    stmtAddStampante.setString(1, nuova.getMarca());
                    stmtAddStampante.setString(2, nuova.getModello());
                    stmtAddStampante.setString(3, nuova.getUrlImmagine());
                    stmtAddStampante.setString(4, nuova.getTipoStampa());
                    stmtAddStampante.setString(5, nuova.getGammaColori());
                    stmtAddStampante.setString(6, nuova.getDescrizione());
                    stmtAddStampante.setDouble(7, nuova.getPrezzoUnitario());
                    stmtAddStampante.setInt(8, nuova.getQuantita());
                    stmtAddStampante.setInt(9, nuova.getVenditoreId());
                    int numRighe = stmtAddStampante.executeUpdate();
                    ResultSet nuovoId = stmtAddStampante.getGeneratedKeys();
                    if (nuovoId != null && nuovoId.next()) {
                         id = nuovoId.getInt(1);
                    }
                    if (numRighe != 1) return false;
                }
                String queryAltreCaratteristiche = 
                    "SELECT id FROM altre_caratteristiche_stampante " +
                    "WHERE caratteristica = ? ";
                try (PreparedStatement stmtAltreCaratteristiche = connessione.prepareStatement(queryAltreCaratteristiche)){      
                    for (String s: nuova.getAltreCaratteristiche()){
                        stmtAltreCaratteristiche.setString(1, s);
                        ResultSet setAltreCaratteristiche = stmtAltreCaratteristiche.executeQuery();
                        String queryAddCaratteristiche = 
                                "INSERT INTO stampanti_altreCaratteristiche" + 
                                "(stampanti_in_vendita_id, altre_caratteristiche_stampante_id) VALUES(?, ?)"; 
                         try(PreparedStatement stmtAddCaratteristiche = connessione.prepareStatement(queryAddCaratteristiche)){
                             if (id != -1){
                                stmtAddCaratteristiche.setInt(1, id);
                                if (setAltreCaratteristiche.next()){
                                    stmtAddCaratteristiche.setInt(2, setAltreCaratteristiche.getInt("id"));
                                }
                                stmtAddCaratteristiche.executeUpdate();
                             }  
                         }         
                    }
                }
        } catch (SQLException e) {
            e.getCause();
            return false;
        }
         return true;
    }

    public boolean modifyStampanteInVendita(StampanteInVendita stampante){
            try (Connection connessione = DriverManager.getConnection(connectionString, "stampantisrldb", "aaabbb")) {
                String queryDeleteCaratteristiche = 
                    "DELETE FROM stampanti_altreCaratteristiche WHERE stampanti_in_vendita_id = ? ";
                try (PreparedStatement stmtDeleteCaratteristiche = connessione.prepareStatement(queryDeleteCaratteristiche)) {
                    stmtDeleteCaratteristiche.setInt(1, stampante.getId());
                    stmtDeleteCaratteristiche.executeUpdate();
                }
                String queryModStampante = 
                    "UPDATE stampanti_in_vendita SET " + 
                    "marca = ?, modello = ?, url_immagine = ?, " +
                    "tipo_stampa = ?, gamma_colori = ?, descrizione = ?, " +
                    "prezzo_unitario = ?, quantita = ?, venditore_id = ? " +
                    "WHERE id = ? ";
                try (PreparedStatement stmtModStampante = connessione.prepareStatement(queryModStampante)) {
                    stmtModStampante.setString(1, stampante.getMarca());
                    stmtModStampante.setString(2, stampante.getModello());
                    stmtModStampante.setString(3, stampante.getUrlImmagine());
                    stmtModStampante.setString(4, stampante.getTipoStampa());
                    stmtModStampante.setString(5, stampante.getGammaColori());
                    stmtModStampante.setString(6, stampante.getDescrizione());
                    stmtModStampante.setDouble(7, stampante.getPrezzoUnitario());
                    stmtModStampante.setInt(8, stampante.getQuantita());
                    stmtModStampante.setInt(9, stampante.getVenditoreId());
                    stmtModStampante.setInt(10, stampante.getId());
                    int numRighe = stmtModStampante.executeUpdate();
                   if (numRighe != 1) return false;
                }
                String queryAltreCaratteristiche = 
                    "SELECT id FROM altre_caratteristiche_stampante " +
                    "WHERE caratteristica = ? ";
                try (PreparedStatement stmtAltreCaratteristiche = connessione.prepareStatement(queryAltreCaratteristiche)){      
                    for (String s: stampante.getAltreCaratteristiche()){
                        stmtAltreCaratteristiche.setString(1, s);
                        ResultSet setAltreCaratteristiche = stmtAltreCaratteristiche.executeQuery();
                        String queryAddCaratteristiche = 
                                "INSERT INTO stampanti_altreCaratteristiche" + 
                                "(stampanti_in_vendita_id, altre_caratteristiche_stampante_id) VALUES(?, ?)"; 
                        try (PreparedStatement stmtAddCaratteristiche = connessione.prepareStatement(queryAddCaratteristiche)) {
                            stmtAddCaratteristiche.setInt(1, stampante.getId());
                            if (setAltreCaratteristiche.next()) {
                                stmtAddCaratteristiche.setInt(2, setAltreCaratteristiche.getInt("id"));
                            }
                            stmtAddCaratteristiche.executeUpdate();
                        }      
                    }
                }
                
        } catch (SQLException e) {
            e.getCause();
            return false;
        }
         return true;
    }
    
    public boolean deleteStampanteInVendita(int idStampante, int idVenditore){
        try (Connection connessione = DriverManager.getConnection(connectionString, "stampantisrldb", "aaabbb")) {
            String queryDeleteCaratteristiche = 
                    "DELETE FROM stampanti_altreCaratteristiche WHERE stampanti_in_vendita_id = ? ";
            try (PreparedStatement stmtDeleteCaratteristiche = connessione.prepareStatement(queryDeleteCaratteristiche)) {
                stmtDeleteCaratteristiche.setInt(1, idStampante);
                stmtDeleteCaratteristiche.executeUpdate();
            }
            String queryDeleteStampante = 
                    "DELETE FROM stampanti_in_vendita WHERE id = ? AND venditore_id = ?";
            try (PreparedStatement stmtDeleteStampante = connessione.prepareStatement(queryDeleteStampante)) {
                stmtDeleteStampante.setInt(1, idStampante);
                stmtDeleteStampante.setInt(2, idVenditore);
                int numRighe = stmtDeleteStampante.executeUpdate();
                if (numRighe != 1) return false;
            }
        } catch (SQLException e) {
            e.getCause();
            return false;
        }
        return true;
    } 
    public void setConnectionString(String s){
    this.connectionString = s;
    }   
    public String getConnectionString(){
    return this.connectionString;
    }
}
