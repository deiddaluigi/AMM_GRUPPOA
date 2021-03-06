package com.stampantiSrl.classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luigi Deidda
 */
public class UtentiFactory {
    private static UtentiFactory singleton;
    private String connectionString;
    
    private UtentiFactory(){
    }
    public static UtentiFactory getInstance() {
        if (singleton == null) {
            singleton = new UtentiFactory();
        }
        return singleton;
    }
     public Account getAccount(String username, String password) {
        try (Connection connessione = DriverManager.getConnection(connectionString, "stampantisrldb", "aaabbb")){

            String queryAccount = "select * from accounts where "
                    + "username = ? and password = ?";
            PreparedStatement stmtAccount = connessione.prepareStatement(queryAccount);

            stmtAccount.setString(1, username);
            stmtAccount.setString(2, password);
            ResultSet resAccount = stmtAccount.executeQuery();   
            if(resAccount.next()) {
                if ((resAccount.getString("tipo_utente")).equals("venditore")){
                    Venditore venditore = null;
                    try (Statement stmtVenditore = connessione.createStatement()) {
                        String queryVenditore = "select * from "
                                + "venditori where account_id = " + resAccount.getInt("id");
                        ResultSet resVenditore = stmtVenditore.executeQuery(queryVenditore);
                        if (resVenditore.next()){
                            venditore = new Venditore(resVenditore.getString("ragione_sociale"));
                            venditore.setId(resVenditore.getInt("account_id"));
                            venditore.setP_iva(resVenditore.getString("partita_iva"));
                            venditore.setUsername(resAccount.getString("username"));
                            venditore.setPassword(resAccount.getString("password"));
                        }      
                    }
                    stmtAccount.close();
                    connessione.close();
                    return venditore;    
                } else {
                    Cliente cliente = null;
                    try (Statement stmtCliente = connessione.createStatement()) {
                        String queryCliente = "select * from "
                                + "clienti where account_id = " + resAccount.getInt("id");
                        ResultSet resCliente = stmtCliente.executeQuery(queryCliente);
                        if (resCliente.next()){
                            cliente = new Cliente(resCliente.getString("codice_fiscale"));
                            cliente.setId(resCliente.getInt("account_id"));
                            cliente.setNomeCognome(
                                resCliente.getString("nome"),
                                resCliente.getString("cognome"));
                            cliente.setUsername(resAccount.getString("username"));
                            cliente.setPassword(resAccount.getString("password"));
                        }                   
                    }
                    stmtAccount.close();
                    connessione.close();
                    return cliente;
                }
            } else {
                return null;
            }  
        } 
        catch (SQLException e) {
           String msg = e.getMessage();
        }
        return null;
    }
     public void closeFactoryInstance(){
        try {
            singleton.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(StampantiInVenditaFactory.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void setConnectionString(String s){
    this.connectionString = s;
    }
    
    public String getConnectionString(){
    return this.connectionString;
    }
}
