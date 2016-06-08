<%-- 
    Document   : messaggiErrore
    Created on : 30-apr-2016, 18.27.32
    Author     : Luigi Deidda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test = "${erroreInput_quantita}">
<p class="messaggi_errore">
    Errore input: il campo quantità deve contenere valori interi positivi. 
</p>
</c:if>
<c:if test = "${erroreInput_prezzo}">
<p class="messaggi_errore">
    Errore input: il campo prezzo unitario deve contenere valori espressi in cifre. 
</p>
</c:if>