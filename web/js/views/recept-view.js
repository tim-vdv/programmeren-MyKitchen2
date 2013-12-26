ReceptList = Backbone.View.extend({
    el: '.page',
    render: function() {
        var that = this;
        var recepten = new Recepten();
        recepten.fetch({
            success: function(recepten) {
                var template = _.template($('#recept-list-template').html(), {recepten: recepten.models});
                that.$el.html(template);
               }
        });
    }
});


var receptList = new ReceptList();