$(document).ready(function ()
{
    $("#filtra_id").keyup( function ()
    {
        var q = $("#filtra_id").val();
        eseguiFiltro(q);
    });
    $("body").load(eseguiFiltro(""));
});
function eseguiFiltro(q)
{
    $.ajax(
            {
                url: "filter.json",
                data: {
                    eseguiRicerca: "yes",
                    testo: q
                },
                dataType: 'json',
                success: function (resLista) {
                    $("#msg_non_trovato").remove();
                    $("p.input_filter").remove();
                    $(".riga_tab").remove();
                    if (jQuery.isEmptyObject(resLista)) {
                        $(".riga_tab").remove();
                        var tagP = document.createElement("p");
                        $(".input_filter").append(tagP);
                        tagP.setAttribute("id", "msg_non_trovato");
                        var msg = document.createTextNode("Nessun elemento trovato");
                        tagP.appendChild(msg);
                    } else {
                        aggiornaElencoStampantiInVendita(resLista);
                    }
                },
                error: function () {
                    alert("Si è verificato un errore in fase di aggiornamento pagina");
                }
            });
    function aggiornaElencoStampantiInVendita(listaStampanti)
    {
        
        for (var i in listaStampanti)
        {
            var nuovoTag_tr = document.createElement("tr");
            nuovoTag_tr.setAttribute("class", "riga_tab");
            
            var marca = listaStampanti[i].marca;
            var modello = listaStampanti[i].modello;
            var tipo_stampa = listaStampanti[i].tipo_stampa;
            var marcaModello = marca + " " + modello + " - " + tipo_stampa;
            
            var tdImmagine = document.createElement("td");
            nuovoTag_tr.appendChild(tdImmagine);
            var img = document.createElement("img");
            img.setAttribute("src", listaStampanti[i].url);
            img.setAttribute("alt", marcaModello);
            img.setAttribute("title", marcaModello);
            img.setAttribute("height", "80");
            tdImmagine.appendChild(img);
            
            var tdMarcaModello = document.createElement("td");
            nuovoTag_tr.appendChild(tdMarcaModello);
            var marcaModelloNode = document.createTextNode(marcaModello);
            tdMarcaModello.appendChild(marcaModelloNode);
            
            var tdQt = document.createElement("td");
            nuovoTag_tr.appendChild(tdQt);
            var qt = document.createTextNode(listaStampanti[i].quantita);
            tdQt.appendChild(qt);
            
            var tdPrezzo = document.createElement("td");
            nuovoTag_tr.appendChild(tdPrezzo);
            var prezzo = document.createTextNode("€ "+listaStampanti[i].prezzo);
            tdPrezzo.appendChild(prezzo);
            var tdCarrello = document.createElement("td");
            nuovoTag_tr.appendChild(tdCarrello);
            if (listaStampanti[i].quantita > 0) {
                var link = document.createElement("a");
                link.setAttribute("href", "./sceltaStampante.html?stampante_selezionata=" + listaStampanti[i].id);
                tdCarrello.appendChild(link);
                var imgLink = document.createElement("img");
                imgLink.setAttribute("title", "Aggiungi al carrello");
                imgLink.setAttribute("src", "./icone/icona_carrello_agg.png");
                imgLink.setAttribute("height", 25);
                link.appendChild(imgLink);
            }
            $("table").append(nuovoTag_tr);
        }
    }
}