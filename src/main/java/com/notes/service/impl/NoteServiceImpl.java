package com.notes.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.notes.model.Note;
import com.notes.service.NoteService;

@Service("noteService")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class NoteServiceImpl implements NoteService {

	/*
	@Autowired
	private NoteDao dao;

	@Override
	public List<Note> findAllNotes() {
		return dao.findAllNotes();
	}

	@Override
	public Note findById(long id) {
		return dao.findById(id);
	}

	@Override
	public Note findByTitle(String title) {
		return dao.findByTitle(title);
	}

	@Override
	public void saveNote(Note note) {
		dao.saveNote(note);
	}

	@Override
	public void updateNote(Note note) {
		dao.updateNote(note);
	}

	@Override
	public void deleteNoteById(long id) {
		dao.deleteNoteById(id);
	}

	@Override
	public List<Note> findAllNotesByEmailId(String emailId) {
		return dao.findAllNotesByEmailId(emailId);
	}

	@Override
	public boolean isNoteExist(Note note) {
		return dao.findByTitle(note.getNoteTitle()) != null;
	}*/

	private static final AtomicLong counter = new AtomicLong();
	private static List<Note> notes;

	static {
		notes = createNoteList();
	}

	@Override
	public List<Note> findAllNotes() {
		return notes;
	}

	@Override
	public Note findById(long id) {
		for (Note note : notes) {
			if (note.getId() == id) {
				return note;
			}
		}
		return null;
	}

	@Override
	public Note findByTitle(String title) {
		for (Note note : notes) {
			if (note.getNoteTitle().equalsIgnoreCase(title)) {
				return note;
			}
		}
		return null;
	}

	@Override
	public void saveNote(Note note) {
		note.setId(counter.incrementAndGet());
		notes.add(note);
	}

	@Override
	public void updateNote(Note note) {
		int index = notes.indexOf(note);
		notes.set(index, note);
	}

	@Override
	public void deleteNoteById(long id) {
		for (Iterator<Note> iterator = notes.iterator(); iterator.hasNext();) {
			Note note = iterator.next();
			if (note.getId() == id) {
				iterator.remove();
			}
		}
	}

	@Override
	public List<Note> findAllNotesByEmailId(String emailId) {
		List<Note> newNotes = new ArrayList<Note>();
		for (Note note : notes) {
			if (note.getEmailId().equalsIgnoreCase(emailId)) {
				newNotes.add(note);
			}
		}
		return newNotes;
	}

	private static List<Note> createNoteList() {
		Note noteUser1 = new Note(1, "India",
				"Sadly, no. Instead, the large majority are busy writing layers of complex specifications for doing this stuff in a different way that isn't nearly as useful or eloquent. Nouns aren't universal and verbs aren't polymorphic. We're throwing out decades of real field usage and proven technique and starting over with something that looks a lot like other systems that have failed in the past. We're using HTTP but only because it helps us talk to our network and security people less. We're trading simplicity for flashy tools and wizards",
				"drew@gotprint.com");
		Note noteUser2 = new Note(4, "China",
				"Each of the systems would get information from each other using a simple HTTP GET. If one system needs to add something to another system, it would use an HTTP POST. If a system wants to update something in another system, it uses an HTTP PUT. The only thing left to figure out is what the data should look like",
				"drew@gotprint.com");
		Note noteUser3 = new Note(3, "Nepal",
				"Because web pages are designed to be understood by people. A machine doesn't care about layout and styling. Machines basically just need the data. Ideally, every URL would have a human readable and a machine readable representation. When a machine GETs the resource, it will ask for the machine readable one. When a browser GETs a resource for a human, it will ask for the human readable one",
				"vinayak@gotprint.com");
		Note noteUser4 = new Note(2, "Bhutan",
				"Think about when you're browsing around amazon.com looking for things to buy me for Christmas. Imagine each of the products as being nouns. Now, if they were available in a representation that a machine could understand, you could do a lot of neat things",
				"vinayak@gotprint.com");

		List<Note> notes = new ArrayList<Note>();
		notes.add(noteUser1);
		notes.add(noteUser2);
		notes.add(noteUser3);
		notes.add(noteUser4);
		return notes;
	}

	@Override
	public boolean isNoteExist(Note note) {
		return findByTitle(note.getNoteTitle()) != null;
	}
}
