package com.stampantiSrl.classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luigi Deidda
 */
public class  RegistraPagamento {
    public boolean registraPagamento(
            int idCliente, int idVenditore,double importoPagamento, ArrayList<StampanteInVendita> stampantiNelCarrello ) {
        try (Connection connessione = DriverManager.getConnection(
                UtentiFactory.getInstance().getConnectionString(), "stampantisrldb", "aaabbb")) {
            PreparedStatement stmtStampante = null;
            PreparedStatement stmtContoCliente = null;
            PreparedStatement stmtContoVenditore = null;
            
            String queryStampante 
                    = "UPDATE stampanti_in_vendita SET quantita = quantita - ? WHERE id = ? AND quantita >= ?";
            String queryContoCliente 
                    = "UPDATE conti_correnti SET saldo = saldo - ? WHERE account_id = ? AND saldo >= ? ";
            String queryContoVenditore 
                    = "UPDATE conti_correnti SET saldo = saldo + ? WHERE account_id = ? AND ? >= 0";
            try {
                connessione.setAutoCommit(false);
                
                stmtContoCliente = connessione.prepareStatement(queryContoCliente);
                stmtContoVenditore = connessione.prepareStatement(queryContoVenditore);
                
                stmtContoCliente.setDouble(1, importoPagamento);
                stmtContoCliente.setInt(2, idCliente);
                stmtContoCliente.setDouble(3, importoPagamento);
                
                stmtContoVenditore.setDouble(1, importoPagamento);
                stmtContoVenditore.setInt(2, idVenditore);
                stmtContoVenditore.setDouble(3, importoPagamento);
                int n = 0;
                int n1 = stmtContoCliente.executeUpdate();
                int n2 = stmtContoVenditore.executeUpdate();
                int n3 = 0;
                for (StampanteInVendita s: stampantiNelCarrello){
                    stmtStampante = connessione.prepareStatement(queryStampante);
                    stmtStampante.setInt(1, s.getQuantita());
                    stmtStampante.setInt(2, s.getId());
                    stmtStampante.setInt(3, s.getQuantita());
                    n3 += stmtStampante.executeUpdate();
                    n++;
                }
    
                if (n1 != 1 || n2 != 1 || n3 != n) {
                    connessione.rollback();
                   throw new RuntimeException("Errore nella registrazione pagamento. Il pagamento è stato annullato.");
                }
                else {
                    connessione.commit();
                }
                
            } catch (SQLException e) {
                    e.getMessage();
               try {
                   connessione.rollback();
               } catch (SQLException errorRollback){
                   errorRollback.getMessage();
               }
               return false;
            } finally {
                if (stmtStampante != null) stmtStampante.close();
                if (stmtContoCliente != null) stmtContoCliente.close();
                if (stmtContoVenditore != null) stmtContoVenditore.close();
                connessione.setAutoCommit(true);
                connessione.close();
            }
        } catch (SQLException ex) {
            return false;
        } 
        return true;
    }
}

