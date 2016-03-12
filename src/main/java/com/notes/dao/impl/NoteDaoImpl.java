package com.notes.dao.impl;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.notes.dao.AbstractDao;
import com.notes.dao.NoteDao;
import com.notes.model.Note;

@Repository("noteDao")
public class NoteDaoImpl extends AbstractDao implements NoteDao {

	@Override
	public Note findById(long id) {
		Criteria criteria = getSession().createCriteria(Note.class);
		criteria.add(Restrictions.eq("id",id));
        return (Note) criteria.list().get(0);
	}

	@Override
	public Note findByTitle(String title) {
		Criteria criteria = getSession().createCriteria(Note.class);
		criteria.add(Restrictions.eq("noteTitle",title));
		
        List result= criteria.list();
        
        if(result != null && !result.isEmpty()){
        	return (Note) result.get(0);
        }
        return null;
	}

	@Override
	public void saveNote(Note note) {
		getSession().saveOrUpdate(note);
	}

	@Override
	public void updateNote(Note note) {
		 getSession().update(note);
	}

	@Override
	public void deleteNoteById(long id) {
		 Query query = getSession().createSQLQuery("delete from note where id = :id");
	        query.setLong("id", id);
	        query.executeUpdate();
	}

	@Override
	public List<Note> findAllNotesByEmailId(String emailId) {
		Criteria criteria = getSession().createCriteria(Note.class);
		criteria.add(Restrictions.eq("emailId",emailId));
        return (List<Note>) criteria.list();
	}

	@Override
	public List<Note> findAllNotes() {
		Criteria criteria = getSession().createCriteria(Note.class);
        return (List<Note>) criteria.list();
	}

	@Override
	public boolean isNoteExist(Note note) {
		// TODO Auto-generated method stub
		return false;
	}

}
