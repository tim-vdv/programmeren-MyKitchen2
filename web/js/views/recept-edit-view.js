ReceptEditList = Backbone.View.extend({
    el: '.page',
    render: function(options) {
        var that = this;
        var recept = new Recept({id: options.id});
        recept.fetch({
            success: function(recept){
                var template = _.template($('#recept-edit-template').html(),{recept: recept});
                that.$el.html(template);  
            }
        })
        
    }
});


var receptEditList = new ReceptEditList();

function saveRecept(id){
    var recept = new Recept({id: id});
    var naam = $("#naam").val();
    var kookbeschrijving = $("#kookbeschrijving").val();
    var receptbeschrijving = $("#receptbeschrijving").val();
    console.log(id);
    
    if(naam === "" || kookbeschrijving === "" || receptbeschrijving === ""){
        alert("Ongeldige naam, beschrijving of bereiding");
    }
    else{
        recept.save({"naam": naam,"kookBeschrijving":kookbeschrijving,"receptBeschrijving":receptbeschrijving});
        alert("Recept gewijzigd");
        window.location = "adminBewerkenRecepten.html";
    }
}