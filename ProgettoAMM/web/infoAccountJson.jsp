<%-- 
    Document   : infoAccountJson
    Created on : 9-giu-2016, 8.17.43
    Author     : Luigi Deidda
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<json:object>
    <json:property name="tipoUtente" value="${tipoUtente}"/>
    
    <json:property name="cognome" value="${cliente.getCognome()}"/>
    <json:property name="nome" value="${cliente.getNome()}"/>
    <json:property name="cf" value="${cliente.getCF()}"/>
    <json:property name="nContoCliente" value="${contoCliente.getNumeroConto()}"/>
    <json:property name="saldoContoCliente" value="${contoCliente.getSaldo()}"/>
    
    <json:property name="ragioneSociale" value="${venditore.getRagioneSociale()}"/>
    <json:property name="partitaIva" value="${venditore.getP_iva()}"/>
    <json:property name="nContoVenditore" value="${contoVenditore.getNumeroConto()}"/>
    <json:property name="saldoContoVenditore" value="${contoVenditore.getSaldo()}"/>
</json:object>