Router = Backbone.Router.extend({
    routes: {
        "": "gerecht zoeken",
        "ingredienten/edit/:id": "bewerk"
    }
});

router = new Router();

router.on('route:gerecht zoeken', function() {
    ingredientList.render();
});

router.on('route:bewerk', function(id) {
    ingredientEditList.render({id: id});
});

Backbone.history.start();