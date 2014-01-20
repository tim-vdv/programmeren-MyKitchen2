ReceptStapEditList = Backbone.View.extend({
    el: '.page',
    render: function(options) {
        var that = this;
        var receptstap = new Receptstap({receptStapId: options.id});
        receptstap.fetch({
            success: function(receptstap){
                var template = _.template($('#receptstap-edit-template').html(),{receptstap: receptstap});
                that.$el.html(template);  
            }
        })
        
    }
});


var receptstapEditList = new ReceptStapEditList();

function test(id1){
    console.log("succesvolle klik " + id1);
    
}