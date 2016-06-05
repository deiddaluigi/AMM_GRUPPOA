<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>carrello</title>
        <meta charset="UTF-8">
        <meta name="description" content="elenco stampanti in vendita">
        <meta name="author" content="Luigi Deidda">
        <link rel="stylesheet" type="text/css" href="./style.css" media="screen">
    </head>
    <body>
        <div id="page">
            <jsp:include page="header.jsp"/>
            <div class="table_pag">
                <h2>
                    CLIENTE: ${cliente.getNome()} ${cliente.getCognome()}
                </h2>
                <table class="row_color">
                    <tr id="intestazione_tab">
                        <th>Immagine del prodotto</th>
                        <th>Marca e modello</th>
                        <th>Quantit&agrave; di pezzi da acquistare</th>
                        <th>Prezzo unitario</th>
                        <th>Prezzo totale</th>
                        <th>Elimina dal Carrello</th>
                    </tr> 
                    <tr id="abbreviazioni_tab">
                        <th>IMG</th>
                        <th>DESCR</th>
                        <th>QT</th>
                        <th>PR-U</th>
                        <th>PR-TOT</th>
                        <th>ELIM</th>
                    </tr> 
                    <jsp:include page="tabStampantiCarrello.jsp"/>  
                </table>
                <c:if test="${!(acquistato)}">
                <h4 class='datiRiepilogo_acquisto'>Totale &#8364; ${prezzoTotale}</h4>
                <form class='datiRiepilogo_acquisto' method="POST" action="carrello.html?acquistaOk=ok">
                    <p>
                        Per procedere con il pagamento inserire il codice di accesso al conto 
                        <label for="codice_id"></label>
                        <input class="${stile_input_quantita}" type="number" name="codice_accesso" id="codice_id"  min="0" >
                    </p>
                    <p>
                        Per confermare l'acquisto, selezionare
                        <input class="pulsanti" type="submit" value="Acquista" name="acquista_name">
                    </p>
                </form>   
                </c:if>
                <p class="messaggio_acquisto">${messaggio_acquisto}</p>
            </div>
            <div id="blank">
            </div>
            <jsp:include page="footer-1.jsp"/>
            <nav class="barra_navigazione">
                <a href="./">Home</a>
                <a href="./cliente.html">cliente</a>
                <a href="./logout.html">logout</a>
            </nav>
        </div>
    </body>
</html>
