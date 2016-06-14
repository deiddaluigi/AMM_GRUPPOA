function eliminaStampante(idStampante)
{
    var tagDiv = document.createElement("div");
    tagDiv.setAttribute("class", "finestra_messaggio");
    tagDiv.setAttribute("id", "div_finestra_messaggio");
    $("#page").append(tagDiv);
    var tagP = document.createElement("p");
    tagDiv.appendChild(tagP);
   var msg = document.createTextNode(
        "Premere Conferma per eliminare dal database la stampante selezionata (CODICE: " + idStampante + "),\n\
         atrimenti premere Annulla.");
    tagP.appendChild(msg);
    var tagButton1 = document.createElement("button");
    var elimina = "confermaElimina(" + idStampante + ")";
    tagButton1.setAttribute("onclick", elimina);
    tagButton1.appendChild(document.createTextNode("Conferma"));
    tagDiv.appendChild(tagButton1);
    var tagButton2 = document.createElement("button");
    tagButton2.setAttribute("onclick", "annulla()");
    tagButton2.appendChild(document.createTextNode("Annulla"));
    tagDiv.appendChild(tagButton2); 
}
function confermaElimina(idStampante)
{
    location.href = "./venditore.html?stampante_selezionata=" + idStampante + "&elimina=true";
}
function annulla()
{
    $("#div_finestra_messaggio").remove();
}
