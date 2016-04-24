<%-- 
    Document   : login
    Created on : 24-apr-2016, 18.46.58
    Author     : Luigi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>login</title>
        <meta charset="UTF-8">
        <meta name="description" content="inserimento credenziali cliente">
        <meta name="author" content="Luigi Deidda">
        <link rel="stylesheet" type="text/css" href="./style.css" media="screen">
    </head>
    <body>
        <div id="page">
            <div id="header">
            </div>
            <div id="sidebar1">
                <nav>
                    <ul id="sidebar_login">
                    <li><a href="./login.html">Verifica account</a></li>
                    <li><a href="./login.html">Accedi tramite codice</a></li>
                    <li><a href="./login.html">Recupera password</a></li>
                </ul>
                </nav>          
            </div>
            <div id="content">
                <form class="login" method="POST">
                    <h2>Login</h2>
                    <label for="username_id">Username</label>
                    <input type="text" name="username_name" id="username_id" value="inserisci username">
                    <label for="pswd_id">Password</label>
                    <input type="password" name="pswd_name" id="pswd_id" value="non visibile">
                    <input type="submit" value="Accedi"> 
                    <div id="link_registrazione">
                        <p >Non sei ancora registrato?</p>
                        <a href="./login.html">Registrati</a>
                    </div>
                </form> 
                
            </div>
            <div id="blank">
            </div>
            <jsp:include page="footer.jsp"/>
            <nav class="barra_navigazione">
                <a href="./descrizione.html">Descrizione</a>
                <a href="./cliente.html">Cliente</a>
                <a href="./venditore.html">Venditore</a>
            </nav>
            <jsp:include page="vaiAlCarrello.jsp"/>
        </div>
    </body>
</html>