<%--
    Author: Luigi Deidda
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>                        
<p>
    <label for="marca_id">Marca stampante</label>
    <select class="stile_input" name="marca_name" id="marca_id">
        <option value="Canon" 
                <c:if test="${modificaStampante.getMarca().equals('Canon')}">selected</c:if>>Canon</option>
                <option value="Epson"
                <c:if test="${modificaStampante.getMarca().equals('Epson')}">selected</c:if>>Epson</option>
                <option value="Hp"
                <c:if test="${modificaStampante.getMarca().equals('Hp')}">selected</c:if>>Hp</option>
                <option value="Brother" 
                <c:if test="${modificaStampante.getMarca().equals('Brother')}">selected</c:if> >Brother</option>
        </select>
    </p>
    <p>
        <label for="modello_id">Nome stampante</label>
        <input type="text" name="modello_name" id="modello_id" value="${modificaStampante.getModello()}">
    <label for="url_id">URL immagine stampante</label>
    <input type="url" name="url_name" id="url_id" value="${modificaStampante.getUrlImmagine()}">
</p>