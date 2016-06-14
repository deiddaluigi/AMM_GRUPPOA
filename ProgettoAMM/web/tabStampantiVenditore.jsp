<%--
    Author: Luigi Deidda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:forEach var="stampanteVenditore" items="${listaStampantiVenditore}">
    <tr>
        <td> <img   title="${stampanteVenditore.getMarca()} ${stampanteVenditore.getModello()}" height="80" 
                    alt="immagine stampante ${stampanteVenditore.getMarca()} ${stampanteVenditore.getModello()}"
                    src=${stampanteVenditore.getUrlImmagine()}>
        </td>
        <td>(CODICE: ${stampanteVenditore.getId()}) <br/> ${stampanteVenditore.getMarca()} ${stampanteVenditore.getModello()}</td>
        <td>${stampanteVenditore.getQuantita()}</td>
        <td>€ ${stampanteVenditore.getPrezzoUnitario()}</td>
        <td>
            <input  class="link_carrello" value="elimina" type="button" 
                   onclick="eliminaStampante(${stampanteVenditore.getId()})" >
            <a class="link_carrello" href="./venditore.html?stampante_selezionata=${stampanteVenditore.getId()}&modifica=true">modifica</a>
        </td>
    </tr>
</c:forEach>