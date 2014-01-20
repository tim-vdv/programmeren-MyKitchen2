function registreer(){
    console.log("test");
    var gebruiker = new Gebruiker();
    var email = $("#email").val();
    var wachtwoord = $("#wachtwoord").val();
    var wachtwoord1 = $("#wachtwoord1").val();
    if(wachtwoord === wachtwoord1){
        if(email === "" || wachtwoord === ""){
            alert("Ongeldig e-mailadres of wachtwoord");

        }
        else{
            gebruiker.save({"email": email,"wachtwoord":wachtwoord});
            alert("Dag " + email + ", " + "u bent succesvol geregistreerd.");
            window.location = "index.html";
        }
        }
      else{
          alert("De herhaling van het wachtwoord was onjuist");
            $("#wachtwoord1").val("");
            $("#wachtwoord").val("");
      
    }
}