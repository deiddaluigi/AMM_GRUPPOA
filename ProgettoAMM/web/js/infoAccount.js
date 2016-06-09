$(document).ready(function ()
{
    $("#infoAccount_id").click( function ()
    {
        if ($("#finestra_account_id").is(':visible'))
        {
            $("#finestra_account_id").hide();
        } else 
        {
            finestraAccount();
        }
    });
    $(window).resize(function ()
    {
        var pos = $("#infoAccount_id").position();
        $("#finestra_account_id").css("width", larghezzaFinestra() + "px" );
        $("#finestra_account_id").css("left", pos.left - larghezzaFinestra() + "px");
    }
    );
    $("#infoAccount_id").blur(function ()
    {
        $("#finestra_account_id").hide();
    }
    );
});
function larghezzaFinestra()
{
    var largh =  (40 * $(window).width() / 100);
    if (largh > 400) return 400;
    else if (largh < 250) return 250;
    else return largh;
}
function finestraAccount()
{
    if ($("#finestra_account_id").is(':hidden') || $("#finestra_account_id").length === 0)
    {
        var tagDiv = document.createElement("div");
        tagDiv.setAttribute("class", "finestra_account");
        tagDiv.setAttribute("id","finestra_account_id");
        $("#infoAccount_id").after(tagDiv);
        var posizione_icona = $("#infoAccount_id").position();
        $("#finestra_account_id").css("width", larghezzaFinestra() + "px" );
        $("#finestra_account_id").css("left", posizione_icona.left - larghezzaFinestra() + "px" );
        caricaDatiAccount(tagDiv);
    }
}
function caricaDatiAccount(tag)
{
    $.ajax(
            {
                url: "infoAccount.json",
                data: {
                    caricaDati: "yes"
                },
                dataType: 'json',
                success: function (resDati) {
                    aggiornaDati(resDati, tag);
                },
                error: function () {
                    alert("Si è verificato un errore in fase di caricamento dati");
                }
            });
    function aggiornaDati(dati, tag)
    {
        var tagUl = document.createElement("ul");
        tag.appendChild(tagUl);
        tagUl.setAttribute("id", "elencoDatiAccount_id");
        $("#elencoDatiAccount_id").css("list-style-type", "none" );
        $("#elencoDatiAccount_id").css("text-align", "left" );
        if (dati.tipoUtente === "cliente")
        {
            var tagLi1 = document.createElement("li");
            tagUl.appendChild(tagLi1);
            var cognome = document.createTextNode("COGNOME: " + dati.cognome);
            tagLi1.appendChild(cognome);

            var tagLi2 = document.createElement("li");
            tagUl.appendChild(tagLi2);
            var nome = document.createTextNode("NOME: " + dati.nome);
            tagLi2.appendChild(nome);

            var tagLi3 = document.createElement("li");
            tagUl.appendChild(tagLi3);
            var cf = document.createTextNode("CODICE FISCALE: " + dati.cf);
            tagLi3.appendChild(cf);
            
            var tagLi4 = document.createElement("li");
            tagUl.appendChild(tagLi4);
            var nConto = document.createTextNode("NUMERO CONTO: " + dati.nContoCliente);
            tagLi4.appendChild(nConto);
            
            var tagLi5 = document.createElement("li");
            tagUl.appendChild(tagLi5);
            var saldoConto = document.createTextNode("SALDO DEL CONTO: € " + dati.saldoContoCliente);
            tagLi5.appendChild(saldoConto);
            
        } else if (dati.tipoUtente === "venditore")
        {
            var tagLi1 = document.createElement("li");
            tagUl.appendChild(tagLi1);
            var ragioneSociale = document.createTextNode("RAGIONE SOCIALE: " + dati.ragioneSociale);
            tagLi1.appendChild(ragioneSociale);

            var tagLi2 = document.createElement("li");
            tagUl.appendChild(tagLi2);
            var pIVA = document.createTextNode("PARTITA IVA: " + dati.partitaIva);
            tagLi2.appendChild(pIVA);
            
            var tagLi3 = document.createElement("li");
            tagUl.appendChild(tagLi3);
            var nConto = document.createTextNode("NUMERO CONTO: " + dati.nContoVenditore);
            tagLi3.appendChild(nConto);
            
            var tagLi4 = document.createElement("li");
            tagUl.appendChild(tagLi4);
            var saldoConto = document.createTextNode("SALDO DEL CONTO: € " + dati.saldoContoVenditore);
            tagLi4.appendChild(saldoConto);
        }
        
        
        
        $("#elencoDatiAccount_id > li").css("margin-bottom", "10px" );

    }
}



