<%-- 
    Document   : login
    Created on : 24-apr-2016, 18.46.58
    Author     : Luigi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>login</title>
        <meta charset="UTF-8">
        <meta name="description" content="inserimento credenziali">
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
                <form class="login" action="login.html" method="POST">
                    <h2>Login</h2>
                    <label for="username_id">Username</label>
                    <input type="text" name="username_name" id="username_id" value="inserisci username">
                    <label for="pswd_id">Password</label>
                    <input type="password" name="pswd_name" id="pswd_id" value="non visibile">
                    <input type="submit" value="Accedi" name="submit_name">
                    <c:if test = "${flagAccessoNegato}">
                        <p class="messaggi_errore">username e/o password errati</p>
                    </c:if>
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
                <a href="./">Home</a>
            </nav>
        </div>
    </body>
</html>
