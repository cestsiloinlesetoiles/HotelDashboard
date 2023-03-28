package model;
import java.util.*;


public class Options {
	 
    private String name;
    private int price;
    
    // Peut être enlever
    public LuxaryRoom luxaryRoom;
    public PresidentialSuite presidentialSuite;
    
    public Options(String name, int price) {
    	this.price = price;
    	this.name = name;
    }

	public int getCost() {
		return price;
	}

 

}