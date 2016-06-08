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
    if (largh > 350) return 350;
    else if (largh < 200) return 200;
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
        var testo = document.createTextNode("info Account");
        tagDiv.appendChild(testo);
    }
}



