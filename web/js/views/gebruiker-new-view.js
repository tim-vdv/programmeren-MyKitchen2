function save(){
    var gebruiker = new Gebruiker();
    var email = $("#email").val();
    var wachtwoord = $("#wachtwoord").val();
    var gebruikerRole = $("#gebruikerRole").val();
    
    if(email === "" || wachtwoord === ""){
        alert("Ongeldig e-mailadres of wachtwoord");
    }
    else{
        gebruiker.save({"email": email,"wachtwoord":wachtwoord,"gebruikerRole":gebruikerRole});
        alert("Gebruiker toegevoegd");
        window.location = "adminBewerkenGebruikers.html";
    }
}