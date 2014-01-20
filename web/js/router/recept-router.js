Router = Backbone.Router.extend({
    routes: {
        "": "alle gerechten",
        "detail/:id": 'receptDetailList',
        "recepten/:id": "bewerk",
        "home": 'alle gerechten'
    }
});

router = new Router();

router.on('route:bewerk', function(id) {
    receptEditList.render({id: id});
});

router.on('route:alle gerechten', function() {
    receptList.render();
});

router.on('route:receptDetailList', function(id) {
    receptDetailList.render({id: id});
});

Backbone.history.start();