package com.stampantiSrl.servlet;

import com.stampantiSrl.classi.Cliente;
import com.stampantiSrl.classi.ContiCorrentiFactory;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RicaricaContoCorrente", urlPatterns = {"/ricaricaContoCorrente.html"})
public class RicaricaContoCorrente extends HttpServlet {

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
            if (sessione.getAttribute("clienteLoggedIn") != null &&
            (boolean) sessione.getAttribute("clienteLoggedIn")) {
                if(request.getParameter("ricarica_conto_name") != null){
                    request.setAttribute("risposta", "true");
                    int numCartaCredito = -1;
                    double importoRicarica = -1;
                    try {
                        numCartaCredito = Integer.parseInt(request.getParameter("num_carta_di_credito"));
                        importoRicarica = Double.parseDouble(request.getParameter("importo_ricarica"));
                    } catch (NumberFormatException e){
                        request.setAttribute("msg", "Errore formato carta di credito o importo: " + e.getMessage());
                        request.getRequestDispatcher("ricaricaContoCorrente.jsp").forward(request,response);
                    }
                    
                    /*per simulare un criterio di verifica validità dei numeri di carta di credito,
                    valutiamo come carta valida un numero di carta multiplo di 3*/
                    if (numCartaCredito % 3 == 0) {
                        if (importoRicarica > 0) {
                            Cliente cliente = (Cliente) sessione.getAttribute("cliente");
                            if (ContiCorrentiFactory.getInstance().versaSulConto(cliente.getId(), importoRicarica)) {
                                request.setAttribute("msg", "L'importo e' stato accreditato correttamente sul conto.");
                            } else {
                                request.setAttribute("msg", "Errore di sistema. L'importo non e' stato accreditato.");
                            }
                        } else {
                            request.setAttribute("msg", "Errore: l'importo deve essere maggiore di zero.");
                        }
                    } else {
                        request.setAttribute("msg", "Errore: il numero di carta di credito non è valido.");
                    }
                    request.getRequestDispatcher("ricaricaContoCorrente.jsp").forward(request,response);   
                }
                request.setAttribute("risposta", "false");
                request.getRequestDispatcher("ricaricaContoCorrente.jsp").forward(request,response);          
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
