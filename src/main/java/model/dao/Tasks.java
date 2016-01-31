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
@Table(name = "tasks")
public class Tasks {
 
  @Id
  @GeneratedValue
  private int id;
  @ManyToOne
  @JoinColumn(name="person_id")
  private Person person;
  private String description;
  private String skill;
  private int levelSkill;
  private double credibility;
  private String location;
  private int maxWorker;
  private boolean isActive;
  private Timestamp creationDate;
  private Timestamp updateDate;
  private int duration;
  private int limitBudgetPerUser;
  private int status;  
  
  
  
public double getCredibility() {
	return credibility;
}
public void setCredibility(double credibility) {
	this.credibility = credibility;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
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
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getSkill() {
	return skill;
}
public void setSkill(String skill) {
	this.skill = skill;
}
public int getLevelSkill() {
	return levelSkill;
}
public void setLevelSkill(int levelSkill) {
	this.levelSkill = levelSkill;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public int getMaxWorker() {
	return maxWorker;
}
public void setMaxWorker(int maxWorker) {
	this.maxWorker = maxWorker;
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
public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
public int getLimitBudgetPerUser() {
	return limitBudgetPerUser;
}
public void setLimitBudgetPerUser(int limitBudgetPerUser) {
	this.limitBudgetPerUser = limitBudgetPerUser;
}
 
  }