<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
                    <c:forEach var="stampanteCarrello" items="${listaStampantiCarrello}">
                    <tr>
                        <td> <img   title="${stampanteCarrello.getMarca()} ${stampanteCarrello.getModello()}" height="80" 
                                    alt="immagine stampante ${stampanteCarrello.getMarca()} ${stampanteCarrello.getModello()}"
                                    src=${stampanteCarrello.getUrlImmagine()}>
                        </td>
                        <td>${stampanteCarrello.getMarca()} ${stampanteCarrello.getModello()}</td>
                        <td>${stampanteCarrello.getQuantita()}</td>
                        <td>€ ${stampanteCarrello.getPrezzoUnitario()}</td>
                        <td>€ ${stampanteCarrello.getQuantita()*stampanteCarrello.getPrezzoUnitario()}</td> 
                        <td>
                            <a class="link_carrello" href="./carrello.html?stampante_selezionata=${stampanteCarrello.getId()}">elimina</a>
                        </td>
                    </tr>
                    </c:forEach>