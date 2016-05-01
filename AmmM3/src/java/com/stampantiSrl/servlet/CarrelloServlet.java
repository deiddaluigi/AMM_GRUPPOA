/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stampantiSrl.servlet;

import com.stampantiSrl.classi.ContoCliente;
import com.stampantiSrl.classi.ContoVenditore;
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

//Creazione conti corrente
ContoCliente contoCliente = new ContoCliente(001,1000);//codice 001, saldo iniziale 1000 €
ContoVenditore contoVenditore = new ContoVenditore(002,1000);//codice 002, saldo iniziale 1000 €
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
                double prezzoTotale = 0;
                ArrayList<StampanteInVendita> carrello;
                        carrello = (ArrayList<StampanteInVendita>) sessione.getAttribute("carrello");
                if (request.getParameter("stampante_selezionata") != null){ 
                    try {
                        int stampanteSelezionata = Integer.parseInt(request.getParameter("stampante_selezionata"));
                        StampanteInVendita stampante = StampantiInVenditaFactory.getStampanteInVendita(stampanteSelezionata);
                        for(int i=0; i < carrello.size() ; i++){
                            if (carrello.get(i).getId() == stampanteSelezionata ){
                                stampante.setQuantita(stampante.getQuantita()+carrello.get(i).getQuantita());
                                carrello.remove(i);
                                break;
                            }
                        } 
                    } catch (NumberFormatException e){
                        response.sendError(400);
                    }
                } 
                for(int i=0; i < carrello.size() ; i++){
                    prezzoTotale += carrello.get(i).getQuantita() * carrello.get(i).getPrezzoUnitario();
                } 
                if (request.getParameter("acquistaOk") != null && prezzoTotale > 0){
                    if ( contoCliente.getSaldo() >= prezzoTotale){
                       contoCliente.prelevaDaConto(001, prezzoTotale);
                       contoVenditore.versamento(prezzoTotale);
                       request.setAttribute("messaggio_acquisto", "L'acquisto e' andato a buon fine e "
                               + "l'importo dell'acquisto e' stato addebitato sul conto corrente.");
                       carrello.clear();
                       request.setAttribute("acquistato", true);
                    } else {
                    request.setAttribute("messaggio_acquisto", "Non è stato possibile effettuare "
                            + "l'acquisto poiché l'importo supera la disponibilità del conto corrente.");
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
