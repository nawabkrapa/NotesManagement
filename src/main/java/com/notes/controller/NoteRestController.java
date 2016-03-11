package com.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.notes.model.Note;
import com.notes.service.NoteService;

@RestController
public class NoteRestController {
	
	@Autowired
    NoteService noteService;  //Service which will do all data retrieval/manipulation work
	
	 //-------------------Retrieve All Notes--------------------------------------------------------
	 @RequestMapping(value = "/note", method = RequestMethod.GET)  
	 public ResponseEntity<List<Note>> getAllNotes()  
	 {  
		 List<Note> notes = noteService.findAllNotes();
	        if(notes.isEmpty()){
	            return new ResponseEntity<List<Note>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Note>>(notes, HttpStatus.OK);  
	 }  
	 
	 //-------------------Retrieve User All Notes by user Email Id--------------------------------------------------------
	 @RequestMapping(value = "/note/user/{emailId}", method = RequestMethod.GET)  
	 public ResponseEntity<List<Note>> getNotesByEmailId(@PathVariable String emailId)  
	 {   
		 	System.out.println("Fetching Note with email id " + emailId);
		    List<Note> notes = noteService.findAllNotesByEmailId(emailId);
	        if(notes.isEmpty()){
	            return new ResponseEntity<List<Note>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Note>>(notes, HttpStatus.OK); 
	 }  
	 
	 //-------------------Retrieve Single note by note id--------------------------------------------------------
	 @RequestMapping(value = "/note/{id}", method = RequestMethod.GET)  
	 public ResponseEntity<Note> getNoteById(@PathVariable long id)  
	 {  
		 System.out.println("Fetching Note with id " + id);
		 Note note = noteService.findById(id);
	        if (note == null) {
	            System.out.println("Note with id " + id + " not found");
	            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Note>(note, HttpStatus.OK);  
	 } 
	 
	//-------------------Retrieve Single note by note title--------------------------------------------------------
		 @RequestMapping(value = "/note/Title/{title}", method = RequestMethod.GET)  
		 public ResponseEntity<Note> getNoteByTitle(@PathVariable String title)  
		 {  
			 System.out.println("Fetching Note with id " + title);
			 Note note = noteService.findByTitle(title);
		        if (note == null) {
		            System.out.println("Note with id " + title + " not found");
		            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<Note>(note, HttpStatus.OK);  
		 }
	
		 //-------------------Create a Note--------------------------------------------------------	 
	 @RequestMapping(value = "/note", method = RequestMethod.POST)
	    public ResponseEntity<Void> createNote(@RequestBody Note note,    UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating Note " + note.getNoteTitle());
	 
	        if (noteService.isNoteExist(note)) {
	            System.out.println("A note with title " + note.getNoteTitle() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	 
	        noteService.saveNote(note);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/note/{id}").buildAndExpand(note.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	
	//------------------- Update a Note --------------------------------------------------------
     
	    @RequestMapping(value = "/note/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Note> updateNote(@PathVariable("id") long id, @RequestBody Note note) {
	        System.out.println("Updating Note " + id);
	         
	        Note currentNote = noteService.findById(id);
	         
	        if (currentNote==null) {
	            System.out.println("Note with id " + id + " not found");
	            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentNote.setNoteBody(note.getNoteBody());
	         
	        noteService.updateNote(currentNote);
	        return new ResponseEntity<Note>(currentNote, HttpStatus.OK);
	    }
	 
	    //------------------- Delete a Note --------------------------------------------------------
	     
	    @RequestMapping(value = "/note/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Note> deleteNote(@PathVariable("id") long id) {
	        System.out.println("Fetching & Deleting Note with id " + id);
	 
	        Note note = noteService.findById(id);
	        if (note == null) {
	            System.out.println("Unable to delete. Note with id " + id + " not found");
	            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
	        }
	 
	        noteService.deleteNoteById(id);
	        return new ResponseEntity<Note>(HttpStatus.NO_CONTENT);
	    }  
	 



}
