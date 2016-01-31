package model.dao;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "skills")
public class Skills {
 
  @Id
  @GeneratedValue
  private int id;
  @ManyToOne
  @JoinColumn(name="person_id")
  private Person person;
  private String skill;
  private int experience;
  private int availabilityTime;
  private boolean isActive;
  private double credibility;
  private double charge;
  
  
  
  public double getCharge() {
	return charge;
}

public void setCharge(double charge) {
	this.charge = charge;
}

  
public double getCredibility() {
	return credibility;
}

public void setCredibility(double credibility) {
	this.credibility = credibility;
}

public int getAvailabilityTime() {
	return availabilityTime;
}
public void setAvailabilityTime(int availabilityTime) {
	this.availabilityTime = availabilityTime;
}
public int getExperience() {
	return experience;
}
public void setExperience(int experience) {
	this.experience = experience;
}
public boolean isActive() {
	return isActive;
}
public void setActive(boolean isActive) {
	this.isActive = isActive;
}
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
public String getSkill() {
	return skill;
}
public void setSkill(String skill) {
	this.skill = skill;
}
  
  
  
}
