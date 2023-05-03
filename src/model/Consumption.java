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
    	this.setStay(stay);
    }
// SET LE PROD   
    
    public void setProduct(product product) {
    	this.product = product;
    }
    
    // retourne le prix
	public int getCost() {	
		return product.getPrice()*qt;
	}

	public GuestStay getStay() {
		return stay;
	}

	public void setStay(GuestStay stay) {
		this.stay = stay;
	}
    
    
// AJOUTE CONSO DANS LIST CONSO DE GUESTSTAY
    
 

}