package com.stampantiSrl.servlet;

import com.stampantiSrl.classi.StampanteInVendita;
import com.stampantiSrl.classi.StampantiInVenditaFactory;
import com.stampantiSrl.classi.Venditore;
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

/**
 * Gestisce i dati di una stampante inviati tramite form di inserimento o modifica
 */
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
               if(request.getParameter("submit_name_stampante")!= null ||
                    request.getParameter("salva_modifiche_name")!= null){ 
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
                    } 
                    stampante.setAltreCaratteristiche(altreCaratteristiche);
                    stampante.setDescrizione(request.getParameter("descrizione_name"));
                    stampante.setVenditoreId(((Venditore) sessione.getAttribute("venditore")).getId());
                    if(request.getParameter("submit_name_stampante")!= null){
                        try {
                            Double prezzo = Double.parseDouble(request.getParameter("prezzo_name"));
                            if (prezzo >= 0) {
                                stampante.setPrezzoUnitario(prezzo);
                                request.setAttribute("stile_input_prezzo", "stile_input");
                            } else throw new NumberFormatException();
                        } catch (NumberFormatException e) {
                            request.setAttribute("erroreInput_prezzo", true);
                            request.setAttribute("stile_input_prezzo", "errori_input");
                            request.setAttribute("modificaStampante", stampante);
                            request.getRequestDispatcher("./venditore.jsp").forward(request, response);
                        }
                        try {
                            stampante.setQuantita(Integer.parseInt(request.getParameter("quantita_name")));
                            request.setAttribute("stile_input_quantita", "stile_input");
                        } catch (NumberFormatException e) {
                            request.setAttribute("erroreInput_quantita", true);
                            request.setAttribute("stile_input_quantita", "errori_input");
                            request.setAttribute("modificaStampante", stampante);
                            request.getRequestDispatcher("./venditore.jsp").forward(request, response);
                        }
                        request.setAttribute("stampante", stampante);
                        if (StampantiInVenditaFactory.getInstance().addStampanteInVendita(stampante)){
                        request.getRequestDispatcher("finestraRiepilogoDatiStampante.jsp").forward(request, response);
                        } else {
                            response.sendError(501);
                        }
                    } else if (request.getParameter("salva_modifiche_name")!= null){
                        stampante.setId(Integer.parseInt(request.getParameter("id_name")));
                        try {
                            Double prezzo = Double.parseDouble(request.getParameter("prezzo_name"));
                            if (prezzo >= 0) {
                                stampante.setPrezzoUnitario(prezzo);
                                request.setAttribute("stile_input_prezzo", "stile_input");
                            } else throw new NumberFormatException();
                            
                        } catch (NumberFormatException e) {
                            request.setAttribute("erroreInput_prezzo", true);
                            request.setAttribute("stile_input_prezzo", "errori_input");
                            request.setAttribute("modificaStampante", stampante);
                            request.getRequestDispatcher("./stampanteDaModificare.jsp").forward(request, response);
                        }
                        try {
                            stampante.setQuantita(Integer.parseInt(request.getParameter("quantita_name")));
                            request.setAttribute("stile_input_quantita", "stile_input");
                        } catch (NumberFormatException e) {
                            request.setAttribute("erroreInput_quantita", true);
                            request.setAttribute("stile_input_quantita", "errori_input");
                            request.setAttribute("modificaStampante", stampante);
                            request.getRequestDispatcher("./stampanteDaModificare.jsp").forward(request, response);
                        }
                        request.setAttribute("stampante", stampante);
                        if (StampantiInVenditaFactory.getInstance().modifyStampanteInVendita(stampante)) {
                            request.getRequestDispatcher("finestraRiepilogoDatiStampante.jsp").forward(request, response);
                        } else {
                            response.sendError(501);
                        }
                    } else {
                        response.sendError(501);
                    }
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
