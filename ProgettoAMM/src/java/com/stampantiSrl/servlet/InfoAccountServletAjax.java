/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stampantiSrl.servlet;

import com.stampantiSrl.classi.Cliente;
import com.stampantiSrl.classi.ContiCorrentiFactory;
import com.stampantiSrl.classi.ContoCliente;
import com.stampantiSrl.classi.ContoVenditore;
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
 * @author Luigi
 */
@WebServlet(name = "InfoAccountServletAjax", urlPatterns = {"/infoAccount.json"})
public class InfoAccountServletAjax extends HttpServlet {

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
        String caricaDati = request.getParameter("caricaDati");
        if (caricaDati != null) 
        {
            if (caricaDati.equals("yes")) 
            {
                response.setContentType("application/json");
                response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
                response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
                
                HttpSession sessione = request.getSession(false);
                if (sessione.getAttribute("clienteLoggedIn") != null
                        && (boolean) sessione.getAttribute("clienteLoggedIn")) {
                    Cliente cliente = (Cliente) sessione.getAttribute("cliente");
                    ContoCliente contoCliente = (ContoCliente) ContiCorrentiFactory.getInstance().getContoCorrente(cliente);
                    request.setAttribute("tipoUtente", "cliente");
                    request.setAttribute("cliente", cliente);
                    request.setAttribute("contoCliente", contoCliente);
                } else if (sessione.getAttribute("venditoreLoggedIn") != null
                        && (boolean) sessione.getAttribute("venditoreLoggedIn")) {
                    Venditore venditore = (Venditore) sessione.getAttribute("venditore");
                    ContoVenditore contoVenditore = (ContoVenditore) ContiCorrentiFactory.getInstance().getContoCorrente(venditore);
                    request.setAttribute("tipoUtente", "venditore");
                    request.setAttribute("venditore", venditore);
                    request.setAttribute("contoVenditore", contoVenditore);
                } 
                request.getRequestDispatcher("infoAccountJson.jsp").forward(request, response);
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
