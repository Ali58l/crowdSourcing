package model.dao;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "proposal")
public class Proposals {
	
	 @Id
	  @GeneratedValue
	  private int id;
	 @ManyToOne
	 @JoinColumn(name="person_id", nullable=false, updatable=false)
	  private Person person;
	  private double basedProposedPrice;
	  private Category category; 
	  private Timestamp creationDate;
	  private Timestamp updatedDate;
	  private boolean isActive;
	  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public double getBasedProposedPrice() {
		return basedProposedPrice;
	}
	public void setBasedProposedPrice(double basedProposedPrice) {
		this.basedProposedPrice = basedProposedPrice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

}
