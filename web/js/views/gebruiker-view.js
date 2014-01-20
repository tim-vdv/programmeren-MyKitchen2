GebruikerList = Backbone.View.extend({
    el: '.page',
    render: function() {
        var that = this;
        var gebruikers = new Gebruikers();
        gebruikers.fetch({
            success: function(gebruikers) {
                var template = _.template($('#gebruiker-list-template').html(), {gebruiker: gebruikers.models});
                that.$el.html(template);
               }
        });
    }
});


var gebruikerList = new GebruikerList();