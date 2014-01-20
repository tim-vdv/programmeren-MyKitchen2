IngredientList = Backbone.View.extend({
    el: '.page',
    render: function() {
        var that = this;
        var ingredienten = new Ingredienten();
        ingredienten.fetch({
            success: function(ingredienten) {
                var template = _.template($('#ingredient-list-template').html(), {ingredienten: ingredienten.models});
                that.$el.html(template);
               }
        });
    }
});

var ingredientList = new IngredientList();