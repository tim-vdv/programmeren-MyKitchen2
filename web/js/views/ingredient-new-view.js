function saveIngredient(){
    var ingredient = new Ingredient();
    var naam = $("#naam").val();
    
    if(naam === ""){
        alert("Ongeldige naam");
    }
    else{
        ingredient.save({"naam": naam});
        alert("Ingredient toegevoegd");
        location.reload();
    }
}