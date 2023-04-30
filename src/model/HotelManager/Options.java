package model.HotelManager;
import java.util.*;


public class Options {
	 
    public String name;
    public int price;
    
   
    public Options(String name, int price) {
    	this.price = price;
    	this.name = name;
    }

	public int getCost() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

 

}