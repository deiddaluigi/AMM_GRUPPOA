<%--
    Document   : cliente
    Created on : 24-apr-2016, 18.44.42
    Author     : Luigi Deidda
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
        <!-- jQuery -->
        <script type="text/javascript" src="./js/jquery-1.12.4.js"></script>
        <script type="text/javascript" src="./js/filtroStampanti.js"></script>
    </head>
    <body>
        <div id="page">
            <jsp:include page="header.jsp"/>
            <div class="table_pag">
                <div class="input_filter">
                    <label for="filtra_id" id="filtra_label">Filtra</label>
                    <input type="text" name="filtra_name" id="filtra_id">
                </div>
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
                </table>
            </div>
            <div id="blank">
            </div>
            <jsp:include page="footer-1.jsp"/>
            <nav class="barra_navigazione">
                <a class="link_testo_nav" href="./">Home</a>
                <a class="link_testo_nav" href="./logout.html">logout</a>
                <jsp:include page="vaiAlCarrello.jsp"/>
                <jsp:include page="infoAccount.jsp"/>
            </nav>
            
        </div>
    </body>
</html>
