Recepten = Backbone.Collection.extend({
    url: 'api/recepten'
});

Recept = Backbone.Model.extend({
    urlRoot: 'api/recepten',
    label: function () {
        return this.get("naam");
    }
});