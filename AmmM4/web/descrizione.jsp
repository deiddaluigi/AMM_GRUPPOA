<%-- 
    Document   : descrizione
    Created on : 24-apr-2016, 18.41.46
    Author     : Luigi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
-->
<html>
    <head>
        <title>Home Page</title>
        <meta charset="UTF-8">
        <meta name="description" content="descrizione stampanti in vendita">
        <meta name="author" content="Luigi Deidda">
        <link rel="stylesheet" type="text/css" href="./style.css" media="screen">
    </head>
    <body>
        <div id="page">
         <jsp:include page="header.jsp"/>
            <div id="sidebar1">
                <nav>
                    <ul id="sidebar_sommario_id">
                        <li><a href="#descrizione_id">Descrizione</a></li>
                        <li><a href="#vendita_id">Tipologie di stampanti in vendita</a></li>
                        <li><a href="#inkjet_id">Stampanti a getto d'inchiostro</a></li>
                        <li><a href="#laser_id">Stampanti laser</a> <br/></li>
                    </ul>
                </nav>
            </div>
            <div id="content">
                <h1 id="descrizione_id">Descrizione</h1>
                <h2 id="vendita_id">Vendita di stampanti digitali</h2>
                <p>
                    In questo sito si troveranno a disposizione per l'acquisto on-line, 
                    stampanti digitali di varie tecnologie per l'utente consumer e
                    da ufficio. 
                    In particolare saranno acquistabili stampanti a getto d'inchiostro e 
                    laser, con formati A4, A3 e A3+, comprese le versioni wireless e 
                    multifunzione.
                </p>
                <h3 id="inkjet_id">Stampanti a getto d'inchiostro</h3>
                <p>
                    Saranno disponibili stampanti a getto d'inchiostro in bianco e nero
                    per stampanti da ufficio, stampanti consumer e da ufficio a colori e
                    stampanti fotografiche a colori e in scala di grigi, comprese le versioni
                    con cartucce a colori separabili. I modelli saranno con formati di stampa A4, A3 e A3+.
                    Marche: Canon, Epson, HP.
                </p>
                <h3 id="laser_id">Stampanti laser</h3>
                <p>
                    Le stampanti laser saranno disponibili sia monocromatiche che a colori,
                    con modelli di formato stampa A4 e A3. Marche: Brother, Canon, Epson.
                </p>
            </div>
            <div id="blank">
            </div>
            <jsp:include page="footer.jsp"/>
            <nav class="barra_navigazione">
                <c:if test = "${!loggedIn}">
                    <a href='./login.html'>login</a>
                </c:if>
                <c:if test = "${venditoreLoggedIn}">
                    <a href='./venditore.html'>venditore</a>
                    <a href='./logout.html'>logout</a>
                </c:if>
                <c:if test = "${clienteLoggedIn}">
                    <a href='./cliente.html'>cliente</a>
                    <a href='./logout.html'>logout</a>
                </c:if>    
            </nav>
            <c:if test = "${clienteLoggedIn}">
                <jsp:include page="vaiAlCarrello.jsp"/>
            </c:if>  
            
        </div>
    </body>
</html>
