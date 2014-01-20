GebruikerEditList = Backbone.View.extend({
    el: '.page',
    render: function(options) {
        var that = this;
        var gebruiker = new Gebruiker({id: options.id});
        
        gebruiker.fetch({
            success: function(gebruiker){
                var template = _.template($('#gebruiker-edit-template').html(),{gebruiker: gebruiker});
                that.$el.html(template);  
            }
        })
        
    }
});


var gebruikerEditList = new GebruikerEditList();

function saveGebruiker(id){
    var gebruiker = new Gebruiker({id: id});
    var email = $("#email").val();
    var wachtwoord = $("#wachtwoord").val();
    
    if(email === "" || wachtwoord === ""){
        alert("Ongeldig e-mailadres of wachtwoord");
    }
    else{
        gebruiker.save({"email": email,"wachtwoord":wachtwoord});
        alert("Gebruiker gewijzigd");
        window.location = "adminBewerkenGebruikers.html";
    }
}