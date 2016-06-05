<%-- 
    Document   : listaStampantiJson
    Created on : 4-giu-2016, 19.04.07
    Author     : Luigi Deidda
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<json:array>
    <c:forEach var="stampante" items="${listaStampanti}">
        <json:object>
            <json:property name="id" value="${stampante.getId()}"/>
            <json:property name="marca" value="${stampante.getMarca()}"/>
            <json:property name="modello" value="${stampante.getModello()}"/>
            <json:property name="url" value="${stampante.getUrlImmagine()}"/>
            <json:property name="prezzo" value="${stampante.getPrezzoUnitario()}"/>
            <json:property name="quantita" value="${stampante.getQuantita()}"/>
        </json:object>
    </c:forEach>
</json:array>
