package com.stampantiSrl.servlet;

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
@WebServlet(name = "Filter", urlPatterns = {"/filter.json"})
public class Filter extends HttpServlet {

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
        String cerca = request.getParameter("eseguiRicerca");
        if (cerca != null) 
        {
            if (cerca.equals("yes")) 
            {
                response.setContentType("application/json");
                response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
                response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
                String testo = request.getParameter("testo");      
                ArrayList<StampanteInVendita> listaStampanti = 
                        StampantiInVenditaFactory.getInstance().getStampantiInVenditaList(testo);
                HttpSession sessione = request.getSession(false);
                ArrayList<StampanteInVendita> carrello;
                carrello = (ArrayList<StampanteInVendita>) sessione.getAttribute("carrello");
                for (int i = 0; i < carrello.size(); i++) {
                    for (int j = 0; j < listaStampanti.size(); j++){
                        if (listaStampanti.get(j).getId() == carrello.get(i).getId()){
                            int qt = listaStampanti.get(j).getQuantita()
                                    - carrello.get(i).getQuantita();
                            listaStampanti.get(j).setQuantita(qt);
                        }
                    }
                }
                request.setAttribute("listaStampanti", listaStampanti);
                request.getRequestDispatcher("listaStampantiJson.jsp").forward(request, response);
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
