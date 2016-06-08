<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="input_caratteristiche_id">
    <h2>caratteristiche</h2>
    <p>
        <label for="inkjet_id">inkjet</label>
        <input type="radio" name="tipo_stampa_name" id="inkjet_id" value="inkjet" 
               <c:if test="${modificaStampante.getTipoStampa().equals('inkjet')}">checked</c:if>>
               <label for="laser_id">laser</label>
               <input type="radio" name="tipo_stampa_name" id="laser_id" value="laser"
               <c:if test="${modificaStampante.getTipoStampa().equals('laser')}">checked</c:if>> 
        </p>
        <p>
            <label for="colori_id">a colori</label>
            <input type="radio" name="gamma_colori_name" id="colori_id" value="a colori"
            <c:if test="${modificaStampante.getGammaColori().equals('a colori')}">checked</c:if>>
            <label for="monocromatica_id">monocromatica</label>
            <input type="radio" name="gamma_colori_name" id="monocromatica_id" value="monocromatica"
            <c:if test="${modificaStampante.getGammaColori().equals('monocromatica')}">checked</c:if>>
        </p>
        <div class="checkbox">
            <p>
                <label for="multifunzione_id">multifunzione</label>
                <input type="checkbox" name="altre_caratteristiche_name" id="multifunzione_id" value="multifunzione"
                <c:if test= "${modificaStampante.getAltreCaratteristiche().contains('multifunzione')}" >checked</c:if>>  
                <label for="wireless_id">wireless</label>
                <input type="checkbox" name="altre_caratteristiche_name"  id="wireless_id" value="wireless"
                <c:if test= "${modificaStampante.getAltreCaratteristiche().contains('wireless')}" >checked</c:if>>
            </p>
            <p>
                <label for="a3_id">formato A3</label>
                <input type="checkbox" name="altre_caratteristiche_name" id="a3_id" value="A3"
                <c:if test= "${modificaStampante.getAltreCaratteristiche().contains('A3')}" >checked</c:if>>
                <label for="fronteRetro_id">fronte-retro automatico</label>
                <input type="checkbox" name="altre_caratteristiche_name" id="fronteRetro_id" value="fronte-retro automatico"
                <c:if test= "${modificaStampante.getAltreCaratteristiche().contains('fronte-retro automatico')}" >checked</c:if>>
            </p>
        </div>
        <label for="descrizione_id">descrizione</label>
        <textarea class="stile_input" rows="6" cols="30" name="descrizione_name" id="descrizione_id"> 
        ${modificaStampante.getDescrizione()}
    </textarea>
</div>
<div id="pr_q">
    <label for="prezzo_id">prezzo unitario €</label>
    <input class="${stile_input_prezzo}" type="text" name="prezzo_name" id="prezzo_id" value="${modificaStampante.getPrezzoUnitario()}" >
    <label for="quantita_id">quantit&agrave; di pezzi disponibili</label>
    <input class="${stile_input_quantita}" type="number" name="quantita_name" id="quantita_id"  min="0"  value="${modificaStampante.getQuantita()}">
</div>
