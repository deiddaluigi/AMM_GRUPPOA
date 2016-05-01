
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
                    <c:forEach var="stampante" items="${listaStampantiInVendita}">
                    <tr>
                        <td> <img   title="${stampante.getMarca()} ${stampante.getModello()}" height="80" 
                                    alt="immagine stampante ${stampante.getMarca()} ${stampante.getModello()}"
                                    src=${stampante.getUrlImmagine()}>
                        </td>
                        <td>${stampante.getMarca()} ${stampante.getModello()}</td>
                        <td>${stampante.getQuantita()}</td>
                        <td>€ ${stampante.getPrezzoUnitario()}</td>
                        <td> 
                            <input type="hidden" name="stampante_selezionata" id="${stampante.getId()}" />
                             <a href="./sceltaStampante.html?stampante_selezionata=${stampante.getId()}"/>
                                <img title="Aggiungi al carrello" src="./icone/icona_carrello_agg.png" alt="icona carrello" height="25">
                            </a>
                        </td>
                    </tr>
                    </c:forEach>