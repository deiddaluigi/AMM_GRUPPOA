/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stampantiSrl.servlet;

import com.stampantiSrl.classi.Account;
import com.stampantiSrl.classi.Cliente;
import com.stampantiSrl.classi.UtentiFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luigi Deidda
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login.html"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private boolean flagAccessoNegato = false;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        if(request.getParameter("submit_name")!= null){  
            String username = request.getParameter("username_name");
            String password = request.getParameter("pswd_name");
            
            ArrayList<Account> listaAccount = 
                    UtentiFactory.getInstance().getAccountList();
           request.setAttribute("listaAccount", listaAccount);
            for(Account a : listaAccount) {
                if((a.getUsername().equals(username) &&
                   a.getPassword().equals(password))){
                    if ((a instanceof Cliente)){
                        request.setAttribute("cliente", a);
                        request.getRequestDispatcher("cliente.html").forward(request,response);
                    }
                    else{
                        request.setAttribute("venditore", a);
                        request.getRequestDispatcher("venditore.html").forward(request,response);
                    }
                }               
            }
            
                {
                    flagAccessoNegato = true;
                    request.setAttribute("flagAccessoNegato", flagAccessoNegato);
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                }    
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
