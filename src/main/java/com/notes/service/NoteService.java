package com.notes.service;

import java.util.List;

import com.notes.model.Note;

public interface NoteService {
	Note findById(long id);
    
	Note findByTitle(String title);
     
    void saveNote(Note note);
     
    void updateNote(Note note);
     
    void deleteNoteById(long id);
 
    List<Note> findAllNotesByEmailId(String emailId); 
     
	List<Note> findAllNotes();

	boolean isNoteExist(Note note);
}
