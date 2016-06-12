package com.stampantiSrl.servlet;

import com.stampantiSrl.classi.StampanteInVendita;
import com.stampantiSrl.classi.StampantiInVenditaFactory;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SceltaStampante", urlPatterns = {"/sceltaStampante.html"})
public class SceltaStampante extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.CloneNotSupportedException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CloneNotSupportedException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sessione = request.getSession(false);
            if (sessione.getAttribute("clienteLoggedIn") != null &&
            (boolean) sessione.getAttribute("clienteLoggedIn")) {
                try {
                    int stampanteSelezionata = Integer.parseInt(request.getParameter("stampante_selezionata"));
                    StampanteInVendita stampante = StampantiInVenditaFactory.getInstance().getStampanteInVendita(stampanteSelezionata);
                    if(request.getParameter("conferma_name") != null){ 
                        if (stampante.getQuantita() > 0){
                            StampanteInVendita stampanteNelCarrello = (StampanteInVendita) stampante.clone();
                        ArrayList<StampanteInVendita> carrello;
                        carrello = (ArrayList<StampanteInVendita>) sessione.getAttribute("carrello");
                        int qt = 0;
                        for(StampanteInVendita s: carrello){
                            if (s.equals(stampante)){
                                qt = 1;
                                s.setQuantita(s.getQuantita()+1);
                                break;
                            }
                        }
                        if (qt == 0) {
                            stampanteNelCarrello.setQuantita(1);
                            carrello.add(stampanteNelCarrello);
                        }
                        stampante.setQuantita(stampante.getQuantita()-1);
                        sessione.setAttribute("carrello", carrello);
                        request.setAttribute("stampante_aggiunta", true);
                        } else {
                            request.setAttribute("stampante_aggiunta", false);
                        }
                    }
                    request.setAttribute("stampante_selezionata", stampanteSelezionata);
                    request.setAttribute("stampante", stampante);
                } catch (NumberFormatException e){
                    response.sendError(400);
                }           
                request.getRequestDispatcher("sceltaStampante.jsp").forward(request,response);
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
        try {
            processRequest(request, response);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SceltaStampante.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SceltaStampante.class.getName()).log(Level.SEVERE, null, ex);
        }
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
