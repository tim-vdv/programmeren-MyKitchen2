IngredientEditList = Backbone.View.extend({
    el: '.page',
    render: function(options) {
        var that = this;
        var ingredient = new Ingredient({id: options.id});
        ingredient.fetch({
            success: function(ingredient){
                var template = _.template($('#ingredient-edit-template').html(),{ingredient: ingredient});
                that.$el.html(template);  
            }
        })
        
    }
});


var ingredientEditList = new IngredientEditList();

function saveIngredient(id){
    var ingredient = new Ingredient({id: id});
    var naam = $("#naam").val();
    
    if(naam === ""){
        alert("Ongeldige naam");
    }
    else{
        ingredient.save({"naam": naam});
        alert("Ingrediënt gewijzigd");
        window.location = "adminBewerkenIngredienten.html";
    }
}