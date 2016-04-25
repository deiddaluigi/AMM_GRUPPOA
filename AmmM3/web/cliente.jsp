<%--
    Document   : cliente
    Created on : 24-apr-2016, 18.44.42
    Author     : Luigi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>cliente</title>
        <meta charset="UTF-8">
        <meta name="description" content="elenco stampanti disponibili per l'acquisto">
        <meta name="author" content="Luigi Deidda">
        <link rel="stylesheet" type="text/css" href="./style.css" media="screen">
    </head>
    <body>
        <div id="page">
            <div id="header">
            </div>
            <div>
                <table class="row_color">
                    <tr id="intestazione_tab">
                        <th>Immagine del prodotto</th>
                        <th>Marca e modello</th>
                        <th>Quantit&agrave; di pezzi disponibili</th>
                        <th>Prezzo unitario</th>
                        <th>Aggiungi al carrello</th>
                    </tr> 
                    <tr id="abbreviazioni_tab">
                        <th>IMG</th>
                        <th>DESCR</th>
                        <th>QT</th>
                        <th>PRZ</th>
                        <th>AGG</th>
                    </tr> 
                    <c:forEach var="stampanteInVendita" items="${listaStampantiInVendita}">
                    <tr>
                        <td> <img   title="${stampanteInVendita.getMarca()} ${stampanteInVendita.getModello()}" height="80" 
                                    alt="immagine stampante ${stampanteInVendita.getMarca()} ${stampanteInVendita.getModello()}"
                                    src=${stampanteInVendita.getUrlImmagine()}>
                        </td>
                        <td>${stampanteInVendita.getMarca()} ${stampanteInVendita.getModello()}</td>
                        <td>${stampanteInVendita.getQuantita()}</td>
                        <td>€ ${stampanteInVendita.getPrezzoUnitario()}</td>
                        <td> <a href="./cliente.html">
                                <img title="Aggiungi al carrello" src="./icone/icona_carrello_agg.png" alt="icona carrello" height="25">
                            </a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <div id="blank">
            </div>
            <jsp:include page="footer-1.jsp"/>
            <nav class="barra_navigazione">
                <a href="./descrizione.html">Descrizione</a>
                <a href="./Logout">logout</a>
            </nav>
            <jsp:include page="vaiAlCarrello.jsp"/>
        </div>
    </body>
</html>
