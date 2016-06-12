package com.stampantiSrl.servlet;

import com.stampantiSrl.classi.Cliente;
import com.stampantiSrl.classi.ContoCliente;
import com.stampantiSrl.classi.ContiCorrentiFactory;
import com.stampantiSrl.classi.ContoVenditore;
import com.stampantiSrl.classi.RegistraPagamento;
import com.stampantiSrl.classi.StampanteInVendita;
import com.stampantiSrl.classi.StampantiInVenditaFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
                       StampanteInVendita stampante = StampantiInVenditaFactory.getInstance().getStampanteInVendita(stampanteSelezionata);
                       for (int i = 0; i < carrello.size(); i++) {
                           if (carrello.get(i).equals(stampante)) {
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
                       // ipotizzando che appartengano tutte ad uno stesso venditore
                       contoVenditore = ContiCorrentiFactory.getInstance().getContoVenditore(carrello.get(0).getVenditoreId());
                       int codiceAccesso=0;
                       try {
                           try {
                               codiceAccesso = Integer.parseInt(request.getParameter("codice_accesso"));
                           } catch (NumberFormatException e) {
                               request.setAttribute("messaggio_acquisto", 
                                       "Errore. E' stato inserito un codice accesso con formato non valido.");
                           }
                           contoCliente.prelevaDaConto(codiceAccesso, prezzoTotale);
                           contoVenditore.versamento(prezzoTotale);
                           RegistraPagamento pagamento = new RegistraPagamento();
                           if (pagamento.registraPagamento(contoCliente.getId(), contoVenditore.getId(), prezzoTotale, carrello)) {
                               request.setAttribute("messaggio_acquisto", "L'acquisto e' andato a buon fine e "
                                       + "l'importo dell'acquisto e' stato addebitato sul conto corrente.");
                               request.setAttribute("acquistato", true);
                               carrello.clear();
                           } else {
                               request.setAttribute("messaggio_acquisto", "Errore di sistema. Il pagamento è stato annullato.");
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
}
