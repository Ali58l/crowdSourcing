package model.dao;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "auction")
public class Auction {
	
	  @Id 
	  @GeneratedValue
	  private int id;
	  @ManyToOne
	  @JoinColumn(name="person_id")
	  private Person personPriceProposed;
	  private boolean isActive;
	  private Timestamp creationDate;
	  private Timestamp updateDate;
	  @ManyToOne
	  @JoinColumn(name="proposals_id")
	  private Proposals proposals;
	  private double proposedPrice;
	  
	 
	public Proposals getProposals() {
		return proposals;
	}
	public void setProposals(Proposals proposals) {
		this.proposals = proposals;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Person getPersonPriceProposed() {
		return personPriceProposed;
	}
	public void setPersonPriceProposed(Person personPriceProposed) {
		this.personPriceProposed = personPriceProposed;
	}
	public double getProposedPrice() {
		return proposedPrice;
	}
	public void setProposedPrice(double proposedPrice) {
		this.proposedPrice = proposedPrice;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
}
