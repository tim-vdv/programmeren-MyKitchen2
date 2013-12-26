ReceptDetailList = Backbone.View.extend({
    el: '.page',
    render: function(options) {
        var that = this;
        var recept = new Recept({id: options.id});
        recept.fetch({
            success: function(recept){
                var template = _.template($('#recept-detail-template').html(),{recept: recept});
                that.$el.html(template);  
            }
        })
        
    }
});


var receptDetailList = new ReceptDetailList();