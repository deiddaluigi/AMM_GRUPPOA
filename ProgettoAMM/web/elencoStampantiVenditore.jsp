<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>carrello</title>
        <meta charset="UTF-8">
        <meta name="description" content="elenco stampanti venditore">
        <meta name="author" content="Luigi Deidda">
        <link rel="stylesheet" type="text/css" href="./style.css" media="screen">
    </head>
    <body>
        <div id="page">
            <jsp:include page="header.jsp"/>
            <div class="table_pag">
                <form class="datiRiepilogo_acquisto" method="POST" action="./venditore.html">
                Per inserire una nuova stampante, cliccare su 
                <input class="pulsanti" type="submit" value="Inserisci" name="inserisci_name">
                </form>
                <table class="row_color">
                    <tr id="intestazione_tab">
                        <th>Immagine del prodotto</th>
                        <th>Marca e modello</th>
                        <th>Quantit&agrave; di pezzi disponibili</th>
                        <th>Prezzo unitario</th>
                        <th>Opzioni</th>
                    </tr> 
                    <tr id="abbreviazioni_tab">
                        <th>IMG</th>
                        <th>DESCR</th>
                        <th>QT</th>
                        <th>PR-U</th>
                        <th>OPZ</th>
                    </tr> 
                    <jsp:include page="tabStampantiVenditore.jsp"/>  
                </table>
            </div>
            <div id="blank">
            </div>
            <jsp:include page="footer-1.jsp"/>
            <nav class="barra_navigazione">
                <a class="link_testo_nav" href="./">Home</a>
                <a class="link_testo_nav" href="./logout.html">logout</a>
                <jsp:include page="infoAccount.jsp"/>
            </nav>
            <c:if test = "${elimina}" >
                <div class="finestra_messaggio" >
                    <p>${msg}</p>
                    <button onclick="location.href='./venditore.html'" >OK</button>
                </div>
            </c:if>
        </div>
    </body>
</html>
