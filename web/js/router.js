Router = Backbone.Router.extend({
    routes: {
        "": "alle gerechten",
        "detail/:id": 'receptDetailList',
        "home": 'alle gerechten'
    }
});

router = new Router();

router.on('route:alle gerechten', function() {
    receptList.render();
});
router.on('route:receptDetailList', function(id) {
    receptDetailList.render({id: id});
});

Backbone.history.start();