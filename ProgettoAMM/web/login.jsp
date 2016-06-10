<%-- 
    Document   : login
    Created on : 24-apr-2016, 18.46.58
    Author     : Luigi Deidda
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
            <jsp:include page="header.jsp"/>
            <div id="sidebar1">
                <nav>
                    <ul id="sidebar_login">
                    <li><a href="http://www.canon.it/" target="_blank">CANON</a></li>
                    <li><a href="https://www.epson.it/" target="_blank">EPSON</a></li>
                    <li><a href="http://www8.hp.com/it/it/home.html" target="_blank">HP</a></li>
                    <li><a href="http://www.brother.it/" target="_blank">BROTHER</a></li>
                    
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
                </form> 
                
            </div>
            <div id="blank">
            </div>
            <jsp:include page="footer.jsp"/>
            <nav class="barra_navigazione">
                <a class="link_testo_nav" href="./">Home</a>
            </nav>
        </div>
    </body>
</html>
