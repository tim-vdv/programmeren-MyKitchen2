$(window).load(function() {
    var v_aanmelden = document.getElementById("vis_aanmelden");
    var v_afmelden = document.getElementById("vis_afmelden");
    var v_test = document.getElementById("visibletest");
    
    if (document.cookie.indexOf("visited") >= 0) {
        v_aanmelden.style.visibility = "hidden";
        v_afmelden.style.visibility = "visible";
        v_test.style.visibility = "hidden";
    }
    else {
        v_aanmelden.style.visibility = "visible";
        v_afmelden.style.visibility = "hidden";
    }
});

function login() {
    var v_aanmelden = document.getElementById("vis_aanmelden");
    var v_afmelden = document.getElementById("vis_afmelden");
    var email = $("#email").val();
    var wachtwoord = $("#wachtwoord").val();

    var gebruiker = new Gebruikers();
    gebruiker.fetch({
        success: function(gebruiker) {
            var v_email = gebruiker.where({'email': email});
            if (email === "" || wachtwoord === "") {
                alert("Ongeldig e-mailadres of wachtwoord");
            }
            else {
                if (v_email.length === 0) {
                    alert("Ongeldig e-mailadres");
                }
                else {
                    var v_wachtwoord = gebruiker.where({'wachtwoord': wachtwoord});
                    if (v_wachtwoord.length === 0) {
                        alert("Ongeldig wachtwoord");
                    }
                    else {

                        document.cookie = "visited=yes;";
                        alert("Proficiat, u bent ingelogd");
                        v_aanmelden.style.visibility = "hidden";
                        v_afmelden.style.visibility = "visible";
                        
                        
                    }
                }
            }
        }
    });

}