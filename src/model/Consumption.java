package model;
import java.util.*;


public class Consumption {
	
	// Rappel sejour = guestStay
	public int qt;
    public product product;
    public GuestStay stay;
    
    //Default constructor
    public Consumption(int qt) {
    	//this.product = product;
    	this.qt = qt; 
        
    }
    
// SET LE SEJOUR 
    public void setCurrentStay ( GuestStay stay) {
    	this.stay = stay;
    }
// SET LE PROD   
    
    public void setProduct(product product) {
    	this.product = product;
    }
    
    // retourne le prix
	public int getCost() {
		
		// OptionsCost
    	
		int optionsCost = 0;
    	
    	// type element : collection
		
		return product.price*qt;
	}
    
    
// AJOUTE CONSO DANS LIST CONSO DE GUESTSTAY
    
 

}