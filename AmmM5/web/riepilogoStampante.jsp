<%-- 
    Document   : riepilogoStampante
    Created on : 30-apr-2016, 11.36.37
    Author     : Luigi Deidda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="datiRiepilogo">
    <h2>
        Dati di riepilogo della Stampante 
    </h2>
    <c:if test="${not empty stampante.getUrlImmagine()}">
        <img    title="${stampante.getMarca()} ${stampante.getModello()}" 
                height="150" alt="immagine stampante"
                src=${stampante.getUrlImmagine()}>
    </c:if>
        <ul>
            <li><h4>Marca:</h4> ${stampante.getMarca()}</li>
            <li><h4>Modello:</h4>${stampante.getModello()}</li>
            <li><h4>Tecnologia di stampa:</h4> ${stampante.getTipoStampa()}</li>
            <li><h4>Modalità di Stampa:</h4> ${stampante.getGammaColori()}</li>
            <li><h4>Altre caratteristiche:</h4> 
                <c:forEach var="caratteristica" items="${stampante.getAltreCaratteristiche()}" varStatus="i">
                    ${caratteristica}
                   <c:if test="${!(i.last)}">,</c:if>
                </c:forEach>
            </li>
            <li><h4>Descrizione:</h4> ${stampante.getDescrizione()}</li>
            <li><h4>Quantità di pezzi disponibili:</h4> ${stampante.getQuantita()}</li>
            <li><h4>Prezzo unitario:</h4> &#8364; ${stampante.getPrezzoUnitario()}</li>
        </ul>
</div>
