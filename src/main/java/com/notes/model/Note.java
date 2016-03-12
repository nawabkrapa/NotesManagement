package com.notes.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NOTE")
public class Note  extends AbstractTimestampEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -11146023191281816L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "NOTE_ID")
	private long id;
	
	@Column(name = "NOTE_TITLE", nullable = false, unique=true, length=50)
	private String noteTitle;
	
	@Column(name = "NOTE_BODY", nullable = true, length=1000)
	private String noteBody;
		
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "EMAIL_ID", referencedColumnName = "EMAIL_ID") 
	private User emailId;

	public Note(long id, String noteTitle, String noteBody, User emailId) {
		super();
		this.id = id;
		this.noteTitle = noteTitle;
		this.noteBody = noteBody;
//		this.createDate = createDate;
//		this.updateDate = updateDate;
		this.emailId = emailId;
	}
	public long getId() {
		return id;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteBody() {
		return noteBody;
	}

	public void setNoteBody(String noteBody) {
		this.noteBody = noteBody;
	}

	public User getEmailId() {
		return emailId;
	}

	public void setEmailId(User emailId) {
		this.emailId = emailId;
	}
	public Note() {
		super();
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", Title=" + noteTitle + ", NoteBody=" + noteBody + ", Email ID=" + emailId + "]";
	}
}
