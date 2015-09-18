package controller;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proposal")
public class Proposals {
	
	 @Id
	  @GeneratedValue
	  private int id;
	  private Person person;
	  private double proposedPrice;
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
	public double getProposedPrice() {
		return proposedPrice;
	}
	public void setProposedPrice(double proposedPrice) {
		this.proposedPrice = proposedPrice;
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
	  
	  
	  
	  
	
	
	

}
