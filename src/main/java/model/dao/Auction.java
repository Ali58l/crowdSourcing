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
	  private Person personProposed;
	  private String itemName;
	  private String itemDescription;
	  private boolean isActive;
	  private Timestamp creationDate;
	  private Timestamp updateDate;
	  private int countProposals;
	  @ManyToOne
	  @JoinColumn(name="proposals_id")
	  private Proposals proposals;
	  
	 
	public Proposals getProposals() {
		return proposals;
	}
	public void setProposals(Proposals proposal) {
		this.proposals = proposals;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Person getPersonProposed() {
		return personProposed;
	}
	public void setPersonProposed(Person personProposed) {
		this.personProposed = personProposed;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
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
	public int getCountProposals() {
		return countProposals;
	}
	public void setCountProposals(int countProposals) {
		this.countProposals = countProposals;
	}  
	  

}
