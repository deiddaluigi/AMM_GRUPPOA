/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stampantiSrl.servlet;

import com.stampantiSrl.classi.StampanteInVendita;
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
@WebServlet(name = "NuovaStampanteServlet", urlPatterns = {"/nuovaStampante.html"})
public class NuovaStampanteServlet extends HttpServlet {
    StampanteInVendita stampante;
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
               if(request.getParameter("submit_name_stampante")!= null){ 
                stampante = new StampanteInVendita(
                    request.getParameter("marca_name"),
                    request.getParameter("modello_name"));
                    stampante.setUrlImmagine(request.getParameter("url_name"));
                    String tipoStampa;
                    if (request.getParameter("tipo_stampa_name") == null) tipoStampa = "non selezionato";
                    else tipoStampa = request.getParameter("tipo_stampa_name");
                    stampante.setTipoStampa(tipoStampa);
                    String gammaColori;
                    if (request.getParameter("gamma_colori_name") == null) gammaColori = "non selezionato";
                    else gammaColori = request.getParameter("gamma_colori_name");
                    stampante.setGammaColori(gammaColori);
                    String[] checkedbox = request.getParameterValues("altre_caratteristiche_name");
                    ArrayList<String> altreCaratteristiche = new ArrayList<>();
                    if (checkedbox != null) {
                        for (String s: checkedbox){
                            altreCaratteristiche.add(s);
                        }
                    } else altreCaratteristiche.add("nessuna");
                    stampante.setAltreCaratteristiche(altreCaratteristiche);
                    stampante.setDescrizione(request.getParameter("descrizione_name"));
                    try {
                        stampante.setPrezzoUnitario(Double.parseDouble(request.getParameter("prezzo_name")));
                        request.setAttribute("stile_input_prezzo","stile_input");
                    } catch (NumberFormatException e) {
                        request.setAttribute("erroreInput_prezzo", true);
                        request.setAttribute("stile_input_prezzo","errori_input");
                        request.getRequestDispatcher("/venditore.html").forward(request, response);
                        //request.setAttribute("stampante", stampante);
                    }
                    try {
                        stampante.setQuantita(Integer.parseInt(request.getParameter("quantita_name")));
                        request.setAttribute("stile_input_quantita","stile_input");
                    } catch (NumberFormatException e) {
                        request.setAttribute("erroreInput_quantita", true);
                        request.setAttribute("stile_input_quantita","errori_input");
                        request.getRequestDispatcher("/venditore.html").forward(request, response);
                        //request.setAttribute("stampante", stampante);
                    }
                    request.setAttribute("stampante", stampante);
                    request.getRequestDispatcher("finestraRiepilogoDatiStampante.jsp").forward(request, response);
            }          
            }else {
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
