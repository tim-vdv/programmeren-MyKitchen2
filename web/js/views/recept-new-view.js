function saveRecept(){
    var recept = new Recept();
    var naam = $("#naam").val();
    var kookbeschrijving = $("#kookbeschrijving").val();
    var receptbeschrijving = $("#receptbeschrijving").val();
    
    if(naam === "" || kookbeschrijving === "" || receptbeschrijving === ""){
        alert("Ongeldig naam, receptbeschrijving of bereiding");
    }
    else{
        recept.save({"naam": naam,"kookBeschrijving":kookbeschrijving,"receptBeschrijving":receptbeschrijving});
        alert("Recept toegevoegd");
        window.location = "adminBewerkenRecepten.html";
    }
}