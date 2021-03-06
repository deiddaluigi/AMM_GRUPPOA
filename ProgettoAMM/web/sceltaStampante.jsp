<%--
    Author: Luigi Deidda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Riepilogo dati stampante</title>
        <meta charset="UTF-8">
        <meta name="description" content="Riepilogo dati stampante">
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
        <div id="page">
            <jsp:include page="riepilogoStampante.jsp"/>
            <c:if test = "${!(stampante_aggiunta)}">
            <form class="datiRiepilogo" method="POST" action="sceltaStampante.html?stampante_selezionata=${stampante_selezionata}">
                Per aggiungere la stampante al carrello, selezionare
                <input class="pulsanti" type="submit" value="Conferma" name="conferma_name">
            </form>
            </c:if>
            <c:if test = "${stampante_aggiunta}">
            <p class="datiRiepilogo">
                La stampante &egrave; stata aggiunta al carrello.
            </p>
            </c:if>
            <div id="blank"></div>
            <jsp:include page="footer-1.jsp"/>       
        </div>
    </body>
</html>