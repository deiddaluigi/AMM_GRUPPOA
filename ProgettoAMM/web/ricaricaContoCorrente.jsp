<%--
    Author: Luigi Deidda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ricarica Conto Corrente</title>
        <meta charset="UTF-8">
        <meta name="description" content="ricarica conto corrente">
        <meta name="author" content="Luigi Deidda">
        <link rel="stylesheet" type="text/css" href="./style.css" media="screen">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <nav class="barra_navigazione">
            <a class="link_testo_nav" href="./">Home</a>
            <a class="link_testo_nav" href="./cliente.html">cliente</a>
            <a class="link_testo_nav" href="./logout.html">logout</a>
            <jsp:include page="vaiAlCarrello.jsp"/>
            <jsp:include page="infoAccount.jsp"/>
        </nav>
        
        <form id="form_ricarica_conto" class="login" action="ricaricaContoCorrente.html" method="POST">
            <h1 id="titolo_ricarica_conto_id">Ricarica Conto Corrente</h1>
            <label for="num_carta_di_credito_id">Numero di carta di credito</label>
            <input type="text" name="num_carta_di_credito" id="num_carta_di_credito_id">
            <label for="importo_ricarica_id">Importo da Ricaricare (in Euro)</label>
            <input type="text" name="importo_ricarica" id="importo_ricarica_id">
            <input type="submit" value="Esegui la ricarica" name="ricarica_conto_name">
        </form> 
        <c:if test = "${risposta}">
            <p class="messaggio_ricarica">
                ${msg}
            </p>
            </c:if>
    </body>
</html>