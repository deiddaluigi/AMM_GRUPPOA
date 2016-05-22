package com.stampantiSrl.servlet;

import com.stampantiSrl.classi.Account;
import com.stampantiSrl.classi.Cliente;
import com.stampantiSrl.classi.ContiCorrentiFactory;
import com.stampantiSrl.classi.StampanteInVendita;
import com.stampantiSrl.classi.StampantiInVenditaFactory;
import com.stampantiSrl.classi.UtentiFactory;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login.html"}, loadOnStartup = 0)
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
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";
    private boolean flagAccessoNegato = false;
    
    @Override
    public void init(){
    String dbConnection = "jdbc:derby:" +
    this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
    try {
        Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE,
            null, ex);
        }
        UtentiFactory.getInstance().setConnectionString(dbConnection);
        ContiCorrentiFactory.getInstance().setConnectionString(dbConnection);
        StampantiInVenditaFactory.getInstance().setConnectionString(dbConnection);
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            HttpSession sessione = request.getSession(true);
            /*
            Il controllo su sessione.getAttribute("loggedIn")consente di accedere 
            al login solo nel caso non ci sia nessuna sessione gia' attiva.
            Per accedere con altre credenziali si puo' chiudere la sessione corrente
            effettuando il logout e rieffettuando l'accesso tramite il login.
            */
            if (sessione.getAttribute("loggedIn") == null ||
            (boolean)sessione.getAttribute("loggedIn") == false){
                
                if(request.getParameter("submit_name")!= null){ 
                    String username = request.getParameter("username_name");
                    String password = request.getParameter("pswd_name");
                    Account a = UtentiFactory.getInstance().getAccount(username, password);
                        if(a != null){
                            sessione.setAttribute("loggedIn", true);
                            if ((a instanceof Cliente)){
                                sessione.setAttribute("clienteLoggedIn", true);
                                sessione.setAttribute("cliente", a);
                                ArrayList<StampanteInVendita> carrello = new ArrayList<>();
                                sessione.setAttribute("carrello", carrello);
                                sessione.setAttribute("listaAggiornata", false);
                                request.getRequestDispatcher("cliente.html").forward(request,response);
                            }
                            else{
                                sessione.setAttribute("venditoreLoggedIn", true);
                                sessione.setAttribute("venditore", a);
                                request.getRequestDispatcher("venditore.html").forward(request,response);
                            }
                        }               
                    /*flag utilizzato per la visualizzazione del messaggio di errore nel
                     caso di username e/o password errati
                    */
                    flagAccessoNegato = true;
                    request.setAttribute("flagAccessoNegato", flagAccessoNegato);
                    request.getRequestDispatcher("login.jsp").forward(request,response);  
                }
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                /*
                Nel caso si tentasse l'accesso alla pagina di login tramite modalita'
                non previste, mentre una sessione e' ancora attiva, verra' 
                visualizzata direttamente la homepage. In caso di normale
                navigazione, se non e' attiva nessuna sessione, la pagina login.html 
                sarà accessibile direttamente tramite il relativo link nel menu' di 
                navigazione della homepage.
                */
                request.getRequestDispatcher("./").forward(request, response);
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
