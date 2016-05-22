package com.stampantiSrl.servlet;

import com.stampantiSrl.classi.Cliente;
import com.stampantiSrl.classi.ContoCliente;
import com.stampantiSrl.classi.ContiCorrentiFactory;
import com.stampantiSrl.classi.ContoCorrente;
import com.stampantiSrl.classi.ContoVenditore;
import com.stampantiSrl.classi.StampanteInVendita;
import com.stampantiSrl.classi.StampantiInVenditaFactory;
import com.stampantiSrl.classi.UtentiFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luigi Deidda
 */
@WebServlet(name = "CarrelloServlet", urlPatterns = {"/carrello.html"})
public class CarrelloServlet extends HttpServlet {
    ContoCliente contoCliente;
    ContoVenditore contoVenditore;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           HttpSession sessione = request.getSession(false);
           if (sessione.getAttribute("clienteLoggedIn") != null
                   && (boolean) sessione.getAttribute("clienteLoggedIn")) {
               double prezzoTotale = 0;
               ArrayList<StampanteInVendita> carrello;
               carrello = (ArrayList<StampanteInVendita>) sessione.getAttribute("carrello");
               
               //elimina la stampante selezionata dal carrello e aggiorna le quantita'
               if (request.getParameter("elimina_dal_carrello") != null) {
                   try {
                       int stampanteSelezionata = Integer.parseInt(request.getParameter("elimina_dal_carrello"));
                       StampanteInVendita stampante = StampantiInVenditaFactory.getStampanteInVendita(stampanteSelezionata);
                       for (int i = 0; i < carrello.size(); i++) {
                           if (carrello.get(i).getId() == stampanteSelezionata) {
                               stampante.setQuantita(stampante.getQuantita() + carrello.get(i).getQuantita());
                               carrello.remove(i);
                               break;
                           }
                       }
                   } catch (NumberFormatException e) {
                       response.sendError(400);
                   }
               }
               for (int i = 0; i < carrello.size(); i++) {
                   prezzoTotale += carrello.get(i).getQuantita() * carrello.get(i).getPrezzoUnitario();
               }
               if (request.getParameter("acquistaOk") != null && prezzoTotale > 0) {
                   if (sessione.getAttribute("cliente") instanceof Cliente) {

                       //carica l'istanza del cliente relativo alla sessione corrente
                       Cliente cliente = (Cliente) sessione.getAttribute("cliente");

                       //crea un'istanza del conto corrente relativo al cliente corrente
                       contoCliente = (ContoCliente) ContiCorrentiFactory.getInstance().getContoCorrente(cliente);
                       
                       //crea un'istanza del conto del venditore relativo alla prima stampante nel carrello
                       // se appartengono tutte ad uno stesso venditore

                       contoVenditore = ContiCorrentiFactory.getInstance().getContoVenditore(carrello.get(0).getVenditoreId());
                       try {
                           contoCliente.prelevaDaConto(1111, prezzoTotale);                       
                           contoVenditore.versamento(prezzoTotale);
                           try {
                               this.registraPagamento(contoVenditore, contoCliente, carrello);
                               request.setAttribute("messaggio_acquisto", "L'acquisto e' andato a buon fine e "
                                   + "l'importo dell'acquisto e' stato addebitato sul conto corrente.");
                           request.setAttribute("acquistato", true);
                           carrello.clear();
                           } catch (SQLException ex) {
                               Logger.getLogger(CarrelloServlet.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       } catch (RuntimeException e) {
                           request.setAttribute("messaggio_acquisto", e.getMessage());
                       }
                   }
               }
                request.setAttribute("prezzoTotale", prezzoTotale);
                request.setAttribute("listaStampantiCarrello", carrello);
                request.getRequestDispatcher("carrello.jsp").forward(request,response);
            } else {
                response.sendError(401);
            }      
        }       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    public void registraPagamento(
            ContoCorrente contoVenditore, ContoCorrente contoCliente, ArrayList<StampanteInVendita> stampantiNelCarrello ) throws SQLException{
        try (Connection connessione = DriverManager.getConnection(
                UtentiFactory.getInstance().getConnectionString(), "stampantisrl", "aaabbb")) {
            PreparedStatement stmtStampante = null;
            PreparedStatement stmtContoCliente = null;
            PreparedStatement stmtContoVenditore = null;
            
            String queryStampante 
                    = "UPDATE stampanti_in_vendita SET quantita = quantita - ? WHERE id = ?";
            
            String queryContiCorrenti // query uguale per contoVenditore e contoCliente
                    = "UPDATE conti_correnti SET saldo = ? WHERE account_id = ?";
            try {
                connessione.setAutoCommit(false);
                
                stmtContoCliente = connessione.prepareStatement(queryContiCorrenti);
                stmtContoVenditore = connessione.prepareStatement(queryContiCorrenti);
                
                stmtContoCliente.setDouble(1, contoCliente.getSaldo());
                stmtContoCliente.setInt(2, contoCliente.getId());
                
                stmtContoVenditore.setDouble(1, contoVenditore.getSaldo());
                stmtContoVenditore.setInt(2, contoVenditore.getId());
                
                int n1 = stmtContoCliente.executeUpdate();
                int n2 = stmtContoVenditore.executeUpdate();
                int n = 0;
                int n3 = 0;
                for (StampanteInVendita s: stampantiNelCarrello){
                    stmtStampante = connessione.prepareStatement(queryStampante);
                    stmtStampante.setInt(1, s.getQuantita());
                    stmtStampante.setInt(2, s.getId());
                    n3 += stmtStampante.executeUpdate();
                    n++;
                }
    
                if (n1 != 1 || n2 != 1 || n3 != n) connessione.rollback();
                else connessione.commit();
                
            } catch (SQLException e) {
                    e.getErrorCode();
               try {
                   connessione.rollback();
               } catch (SQLException errorRollback){
                   errorRollback.getErrorCode();
               }
            } finally {
                if (stmtStampante != null) stmtStampante.close();
                if (stmtContoCliente != null) stmtContoCliente.close();
                if (stmtContoVenditore != null) stmtContoVenditore.close();
                connessione.setAutoCommit(true);
                connessione.close();
            }
        } 
    }
}
