Gebruikers = Backbone.Collection.extend({
    url: 'api/gebruikers'
});

Gebruiker = Backbone.Model.extend({
    urlRoot: 'api/gebruikers'
}); 