
package com.stampantiSrl.classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Luigi Deidda
 */
public class ContiCorrentiFactory {
    private static ContiCorrentiFactory singleton;
    private String connectionString;

 private ContiCorrentiFactory(){
    }
    public static ContiCorrentiFactory getInstance() {
        if (singleton == null) {
            singleton = new ContiCorrentiFactory();
        }
        return singleton;
    }
    
    public ContoCorrente getContoCorrente(Account utente) {
        try {
            Connection connessione = DriverManager.getConnection(connectionString, "stampantisrldb", "aaabbb");
            try (Statement stmtConto = connessione.createStatement()) {
                String queryConto = "select * from conti_correnti "
                    + "where account_id = " + utente.getId();
                try (ResultSet resConto = stmtConto.executeQuery(queryConto)) {
                    if (resConto.next()){
                        if (utente instanceof Venditore){
                            ContoVenditore contoVenditore = new ContoVenditore(
                                    resConto.getInt("codice_accesso"),resConto.getDouble("saldo"));
                            contoVenditore.setId(resConto.getInt("account_id"));
                            contoVenditore.setNumeroConto(resConto.getInt("numero"));
                            return contoVenditore;
                        } else {
                            ContoCliente contoCliente = new ContoCliente(
                                    resConto.getInt("codice_accesso"),resConto.getDouble("saldo"));
                            contoCliente.setId(resConto.getInt("account_id"));
                            contoCliente.setNumeroConto(resConto.getInt("numero"));
                            return contoCliente;
                        }
                    } else {
                        return null;
                    }
                }    
            }       
        } catch (SQLException e) {
            e.printStackTrace();
            return null;          
        }
    }
    public ContoVenditore getContoVenditore(int id) {
        try {
            Connection connessione = DriverManager.getConnection(connectionString, "stampantisrldb", "aaabbb");
            try (Statement stmtConto = connessione.createStatement()) {
                String queryConto = "SELECT * FROM conti_correnti "
                    + "WHERE account_id = " + id ;
                try (ResultSet resConto = stmtConto.executeQuery(queryConto)) {
                    if (resConto.next()) {
                        ContoVenditore contoVenditore = new ContoVenditore(
                                resConto.getInt("codice_accesso"), resConto.getDouble("saldo"));
                        contoVenditore.setId(resConto.getInt("account_id"));
                        contoVenditore.setNumeroConto(resConto.getInt("numero"));
                        return contoVenditore;
                    } else {
                        return null;
                    }
                }    
            }       
        } catch (SQLException e) {
            e.printStackTrace();
            return null;          
        }
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