<%-- 
    Document   : venditore
    Created on : 24-apr-2016, 18.44.54
    Author     : Luigi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>venditore</title>
        <meta charset="UTF-8">
        <meta name="description" content="inserimento nuove stampanti">
        <meta name="author" content="Luigi Deidda">
        <link rel="stylesheet" type="text/css" href="./style.css" media="screen">
    </head>
    <body>
        <div id="page">
            <jsp:include page="header.jsp"/>
            <div>
                <form method="POST" action="nuovaStampante.html">
                    <div>
                    <h1>Inserimento nuove stampanti</h1>
                    <jsp:include page="messaggiErrore.jsp"/>
                        <p>
                            <label for="marca_id">Marca stampante</label>
                            <select class="stile_input" name="marca_name" id="marca_id">
                                <option value="Canon">Canon</option>
                                <option value="Epson">Epson</option>
                                <option value="Hp">Hp</option>
                                <option value="Brother">Brother</option>
                            </select>
                        </p>
                        <p>
                            <label for="modello_id">Nome stampante</label>
                            <input type="text" name="modello_name" id="modello_id" value="modello stampante">
                            <label for="url_id">URL immagine stampante</label>
                            <input type="url" name="url_name" id="url_id">
                        </p>
                    </div>
                    <div id="input_caratteristiche_id">
                        <h2>caratteristiche</h2>
                        <p>
                            <label for="inkjet_id">inkjet</label>
                            <input type="radio" name="tipo_stampa_name" id="inkjet_id" value="inkjet" >
                            <label for="laser_id">laser</label>
                            <input type="radio" name="tipo_stampa_name" id="laser_id" value="laser"> 
                        </p>
                        <p>
                            <label for="colori_id">a colori</label>
                            <input type="radio" name="gamma_colori_name" id="colori_id" value="colori" >
                            <label for="monocromatica_id">monocromatica</label>
                            <input type="radio" name="gamma_colori_name" id="monocromatica_id" value="monocromatica">
                        </p>
                        <div class="checkbox">
                            <p>
                                <label for="multifunzione_id">multifunzione</label>
                                <input type="checkbox" name="altre_caratteristiche_name" id="multifunzione_id" value="multifunzione">  
                                <label for="wireless_id">wireless</label>
                                <input type="checkbox" name="altre_caratteristiche_name"  id="wireless_id" value="wireless">
                            </p>
                            <p>
                                <label for="a3_id">formato A3</label>
                                <input type="checkbox" name="altre_caratteristiche_name" id="a3_id" value="a3">
                                <label for="fronteRetro_id">fronte-retro automatico</label>
                                <input type="checkbox" name="altre_caratteristiche_name" id="fronteRetro_id" value="fronte retro automatico">
                            </p>
                        </div>
                        <label for="descrizione_id">descrizione</label>
                        <textarea class="stile_input" rows="6" cols="30" name="descrizione_name" id="descrizione_id"> </textarea>
                    </div>
                    <div id="pr_q">
                        <label for="prezzo_id">prezzo unitario €</label>
                        <input class="${stile_input_prezzo}" type="text" name="prezzo_name" id="prezzo_id" >
                        <label for="quantita_id">quantit&agrave; di pezzi disponibili</label>
                        <input class="${stile_input_quantita}" type="number" name="quantita_name" id="quantita_id"  min="0">
                    </div>
                    <div class="input_dati">
                        <input type="submit" value="salva" name="submit_name_stampante">
                        <input type="reset" value="reset">  
                    </div>
                </form> 
            </div>
            <div id="blank">
            </div>
            <jsp:include page="footer-1.jsp"/>
            <nav class="barra_navigazione">
                <a href="./">Home</a>
                <a href="./logout.html">logout</a>
            </nav>
        </div>
    </body>
</html>
