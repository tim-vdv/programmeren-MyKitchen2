$(window).load(function() {
    var v_aanmelden = document.getElementById("vis_aanmelden");
    var v_afmelden = document.getElementById("vis_afmelden");
    var v_koelkast = document.getElementById("vis_koelkast");
    var v_test = document.getElementById("visibletest");

    if (document.cookie.indexOf("gId=0") >= 0) {
        v_aanmelden.style.visibility = "visible";
        v_afmelden.style.visibility = "hidden";
        v_koelkast.style.visibility = "hidden";
        v_test.style.visibility = "visible";

    }
    else {
        v_aanmelden.style.visibility = "hidden";
        v_afmelden.style.visibility = "visible";
        v_koelkast.style.visibility = "visible";
    }
});

function login() {
    var v_aanmelden = document.getElementById("vis_aanmelden");
    var v_afmelden = document.getElementById("vis_afmelden");
    var v_koelkast = document.getElementById("vis_koelkast");
    var email = $("#email").val();
    var wachtwoord = $("#wachtwoord").val();

    var gebruiker = new Gebruikers();
    gebruiker.fetch({
        success: function(gebruiker) {
            if (email === "" || wachtwoord === "") {
                alert("Ongeldig e-mailadres of wachtwoord");
            }
            else {
                try {
                    var v_gebruiker = gebruiker.where({'email': email});
                    var v_email = v_gebruiker[0].get('email');
                    var v_wachtwoord = v_gebruiker[0].get('wachtwoord');
                    var v_role = v_gebruiker[0].get('gebruikerRole');
                    var v_id = v_gebruiker[0].get('id');
                }
                catch (err) {
                    alert("Ongeldig e-mailadres");
                    return;
                }
                if (v_email !== email) {
                    alert("Ongeldig e-mailadres");
                }
                else {
                    if (v_wachtwoord !== wachtwoord) {
                        alert("Ongeldig wachtwoord");
                    }
                    else {
                        if (v_role !== "gebruiker") {
                            document.cookie = "aId=" + v_id + ";";
                            window.location = "admin.html";

                        }
                        else {
                            console.log(v_id);
                            document.cookie = "gId=" + v_id + ";";
                            v_aanmelden.style.visibility = "hidden";
                            v_afmelden.style.visibility = "visible";
                            v_koelkast.style.visibility = "visible";
                        }
                    }
                }
            }
        }
    });

}

function loguit() {
    document.cookie = "gId=0;";
    location.reload();
}
