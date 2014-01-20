Router = Backbone.Router.extend({
    routes: {
        "": "alle receptstappen"
    }
});

router = new Router();

router.on('route:alle receptstappen', function() {
    receptstapList.render();
});

Backbone.history.start();