package model.dao;

import java.util.ArrayList;
import java.util.List;

public class Category {
	
	List<String> categories;
	
	public Category(){
		this.categories = new ArrayList<String>();
		this.categories.add("Computer");
		this.categories.add("House");
		this.categories.add("Education");
		this.categories.add("Intertainment");
		this.categories.add("Sport");
		this.categories.add("Health");
		this.categories.add("Other");
	}
	
	public List<String> returnCategories(){
		
		return categories;
	}

}
