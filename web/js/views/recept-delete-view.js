 function deleteRecept(options){
    var recept = new Recept({id: options});
    if (confirm('Ben je zeker dat je dit recept wilt verwijderen?')) {
        recept.destroy();
    location.reload();
    } else {
        console.log("Niet verwijderd");
    }
    
    
}