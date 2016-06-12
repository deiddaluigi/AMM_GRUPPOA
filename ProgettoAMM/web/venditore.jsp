<%--
    Author: Luigi Deidda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>venditore</title>
        <meta charset="UTF-8">
        <meta name="description" content="inserimento nuove stampanti">
        <meta name="author" content="Luigi Deidda">
        <link rel="stylesheet" type="text/css" href="./style.css" media="screen">
    </head>
    <body>
        <div id="page">
            <jsp:include page="header.jsp"/>
            <div>
                <form class="form_venditore" method="POST" action="nuovaStampante.html">
                    <div>
                    <h1>Inserimento nuove stampanti</h1>
                    <jsp:include page="messaggiErrore.jsp"/>
                    <jsp:include page="sezioneFormInserimentoDati_1.jsp"/>
                    <jsp:include page="sezioneFormInserimentoDati_2.jsp"/>
                    </div>
                    <div class="input_dati">
                        <input type="submit" value="salva" name="submit_name_stampante">
                        <input type="reset" value="reset">  
                    </div>
                </form> 
            </div>
            <div id="blank">
            </div>
            <jsp:include page="footer-1.jsp"/>
            <nav class="barra_navigazione">
                <a class="link_testo_nav" href="./">Home</a>
                <a class="link_testo_nav" href="./venditore.html">elenco stampanti</a>
                <a class="link_testo_nav" href="./logout.html">logout</a>
                <jsp:include page="infoAccount.jsp"/>
            </nav>
        </div>
    </body>
</html>
