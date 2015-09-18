package controller;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auction")
public class Auction {
	
	 @Id
	  @GeneratedValue
	  private int id;
	  private Person personproposed;
	  private String itemName;
	  private String itemDescription;
	  private boolean isActive;
	  private Timestamp creationDate;
	  private Timestamp updateDate;
	  private double basePrice;
	  private Person winner;
	  private double highestPirce;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Person getPersonproposed() {
		return personproposed;
	}
	public void setPersonproposed(Person personproposed) {
		this.personproposed = personproposed;
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
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public Person getWinner() {
		return winner;
	}
	public void setWinner(Person winner) {
		this.winner = winner;
	}
	public double getHighestPirce() {
		return highestPirce;
	}
	public void setHighestPirce(double highestPirce) {
		this.highestPirce = highestPirce;
	}
	  
	  
	  
	  

}
