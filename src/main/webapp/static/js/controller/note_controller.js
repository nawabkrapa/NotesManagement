'use strict';

App.controller('NoteController', ['$scope', 'Note', function($scope, Note) {
          var self = this;
          self.note= new Note();
          
          self.notes=[];
              
          self.fetchAllNotes = function(){
        	  self.notes = Note.query();
          };
           
          self.createNote = function(){
        	  self.note.$save(function(){
        		  self.fetchAllNotes();
        	  });
          };

          self.updateNote = function(){
        	  self.note.$update(function(){
    			  self.fetchAllNotes();
    		  });
          };

         self.deleteNote = function(identity){
        	 var note = Note.get({id:identity}, function() {
        		  note.$delete(function(){
        			  console.log('Deleting note with id ', identity);
        			  self.fetchAllNotes();
        		  });
        	 });
          };

          self.fetchAllNotes();

          self.submit = function() {
              if(self.note.id==null){
                  console.log('Saving New Note', self.note);    
                  self.createNote();
              }else{
    			  console.log('Updating note with id ', self.note.id);
                  self.updateNote();
                  console.log('Note updated with id ', self.note.id);
              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.notes.length; i++){
                  if(self.notes[i].id === id) {
                     self.note = angular.copy(self.notes[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.note.id === id) {//If it is the one shown on screen, reset screen
                 self.reset();
              }
              self.deleteNote(id);
          };

          
          self.reset = function(){
              self.note= new Note();
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
