package controller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
 
  @Id
  @GeneratedValue
  private int id;
  private String name;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		System.out.println(":hello");
		return name;
		
	}
	
	public void setName(String name) {
		this.name = name;
	}	 
}
