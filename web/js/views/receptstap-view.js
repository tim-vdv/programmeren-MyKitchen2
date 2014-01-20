ReceptStapList = Backbone.View.extend({
    el: '.page1',
    render: function() {
        var that = this;
        var receptstappen = new ReceptStappen();
        receptstappen.fetch({
            success: function(receptstappen) {
                var template = _.template($('#receptstap-list-template').html(), {receptstap: receptstappen.models});
                that.$el.html(template);
               }
        });
    }
});


var receptstapList = new ReceptStapList();