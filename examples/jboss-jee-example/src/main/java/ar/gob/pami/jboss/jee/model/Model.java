package ar.gob.pami.jboss.jee.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date updateDate;
	private Date creationDate;

	@PrePersist
	private void preInsert() {
		this.setCreationDate(new Date());
		this.setUpdateDate(new Date());
	}

	@PreUpdate
	private void preUpdate() {
		this.setUpdateDate(new Date());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
