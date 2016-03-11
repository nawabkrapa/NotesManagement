package com.notes.model;

import java.io.Serializable;
import java.util.Date;

//@Entity
//@Table(name = "NOTE")
public class Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -11146023191281816L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
public Note(long id, String noteTitle, String noteBody, String emailId) {
		super();
		this.id = id;
		this.noteTitle = noteTitle;
		this.noteBody = noteBody;
//		this.createDate = createDate;
//		this.updateDate = updateDate;
		this.emailId = emailId;
	}

	//	@Column(name = "NAME", nullable = false, unique=true, length=50)
	private String noteTitle;
	
//	@Column(name = "NOTE_BODY", nullable = true, length=1000)
	private String noteBody;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "CREATE_DATE",nullable=false)
	private Date createDate;

//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "EMAIL_ID", referencedColumnName = "EMAIL_ID") 
	private String emailId;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
