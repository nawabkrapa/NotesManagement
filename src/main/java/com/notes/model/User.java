package com.notes.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User  extends AbstractTimestampEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3433482992205687525L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int id;

	@Column(name = "EMAIL_ID", nullable = false, unique = true, length = 100)
	private String emailId;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "emailId")
    private Set<Note> notes;

	public int getId() {
		return id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", Email Id=" + emailId + "]";
	}

}
