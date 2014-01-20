 function deleteGebruiker(options){
    var gebruiker = new Gebruiker({id: options});
    if (confirm('Ben je zeker dat je deze gebruiker wilt verwijderen?')) {
        gebruiker.destroy();
    location.reload();
    } else {
        console.log("Niet verwijderd");
    }
    
    
}