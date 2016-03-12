package com.notes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractTimestampEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE",nullable=false, updatable=false)
	private Date createDate = new   Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE")
	@Version
	private Date updateDate;

   /* @PrePersist
    protected void onCreate() {
    	updateDate = createDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    	updateDate = new Date();
    }*/

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
