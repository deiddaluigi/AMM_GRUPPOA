package com.stampantiSrl.servlet;

import com.stampantiSrl.classi.StampanteInVendita;
import com.stampantiSrl.classi.StampantiInVenditaFactory;
import com.stampantiSrl.classi.Venditore;
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
@WebServlet(name = "VenditoreServlet", urlPatterns = {"/venditore.html"})
public class VenditoreServlet extends HttpServlet {

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
            if (sessione.getAttribute("venditoreLoggedIn") != null &&
            (boolean) sessione.getAttribute("venditoreLoggedIn")) {
                if(request.getParameter("inserisci_name") == null){ 
                    if (request.getParameter("modifica") != null){
                        if(request.getParameter("modifica").equals("true")){
                            StampanteInVendita stampante = StampantiInVenditaFactory.getStampanteInVendita(
                            Integer.parseInt(request.getParameter("stampante_selezionata")));
                            request.setAttribute("modificaStampante", stampante);
                            request.getRequestDispatcher("./stampanteDaModificare.jsp").forward(request,response);
                        } 
                    }
                    if (request.getParameter("elimina") != null){
                        if(request.getParameter("elimina").equals("true")){
                            StampantiInVenditaFactory.getInstance().deleteStampanteInVendita(
                                Integer.parseInt(request.getParameter("stampante_selezionata")), 
                                ((Venditore) sessione.getAttribute("venditore")).getId());
                            request.setAttribute("elimina", true);
                            request.setAttribute("msg", "la stampante selezionata e' stata eliminata dal database.");
                        } 
                    }
                    request.setAttribute("listaStampantiVenditore", 
                        StampantiInVenditaFactory.getInstance().getStampantiVenditoreList(
                        ((Venditore) sessione.getAttribute("venditore")).getId()));
                    request.getRequestDispatcher("elencoStampantiVenditore.jsp").forward(request,response);
                } else {
                    request.getRequestDispatcher("./venditore.jsp").forward(request,response);
                }
            }else {
                /*
                L'errore 401 verra' visualizzato nel caso si tentasse l'accesso 
                non autenticato tramite modalita' non previste. In caso di normale
                navigazione, la pagina venditore.html sarà accessibile direttamente
                tramite login, o tramite link nel menu' di navigazione della homepage 
                per l'utente autenticato.
                */
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
