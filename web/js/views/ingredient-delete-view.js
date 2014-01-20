 function deleteIngredient(options){
    var ingredient = new Ingredient({id: options});
    if (confirm('Ben je zeker dat je dit ingrediënt wilt verwijderen?')) {
        ingredient.destroy();
    location.reload();
    } else {
        console.log("Niet verwijderd");
    }
    
    
}