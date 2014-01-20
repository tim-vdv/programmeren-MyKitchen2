Router = Backbone.Router.extend({
    routes: {
        "": "alle gebruikers",
        "gebruikers/delete/:id": "delete",
        "gebruikers/:id": "bewerk"
    }
});

router = new Router();

router.on('route:alle gebruikers', function() {
    gebruikerList.render();
});

router.on('route:bewerk', function(id) {
    gebruikerEditList.render({id: id});
});

router.on('route:delete', function(id) {
    gebruikerEditList.render({id: id});
});

Backbone.history.start();