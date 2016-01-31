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
@Table(name = "taskworker")
public class TaskWorker {
 
  @Id
  @GeneratedValue
  private int id;
  @ManyToOne
  @JoinColumn(name="task_id")
  private Tasks task;
  private boolean isActive;
  private Timestamp creationDate;
  private Timestamp updateDate;
  @ManyToOne
  @JoinColumn(name="person_id")
  private Person person;
  private int allocationStatus;	//0= send request from user ::: 1=Accept ::: -1= reject 	
  private double appreciatin;
  private double salaryPaid;
  
  
  
public double getSalaryPaid() {
	return salaryPaid;
}
public void setSalaryPaid(double salaryPaid) {
	this.salaryPaid = salaryPaid;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Tasks getTask() {
	return task;
}
public void setTask(Tasks task) {
	this.task = task;
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
public Person getPerson() {
	return person;
}
public void setPerson(Person person) {
	this.person = person;
}
public int getAllocationStatus() {
	return allocationStatus;
}
public void setAllocationStatus(int allocationStatus) {
	this.allocationStatus = allocationStatus;
}
public double getAppreciatin() {
	return appreciatin;
}
public void setAppreciatin(double appreciatin) {
	this.appreciatin = appreciatin;
}
  
  
  
  
}
