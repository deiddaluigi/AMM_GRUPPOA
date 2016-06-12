<%--
    Author: Luigi Deidda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
-->
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
            <a class="link_testo_nav" href="./venditore.html">venditore</a>
            <a class="link_testo_nav" href="./logout.html">logout</a>
            <jsp:include page="infoAccount.jsp"/>
        </nav>
        <div id="page">
            <p>LA STAMPANTE E' STATA SALVATA CORRETTAMENTE</p>
            <jsp:include page="riepilogoStampante.jsp"/>
            <div id="blank"></div>
            <jsp:include page="footer-1.jsp"/>
        </div>
    </body>
</html>
