package model;
import java.util.*;


public class Consumption {
	
	
	public int qt;
    public Product product;
    public GuestStay stay;
    
    
    public Consumption(int qt) {
    	
    	this.qt = qt; 
        
    }
    

    public void setCurrentStay ( GuestStay stay) {
    	this.setStay(stay);
    }

    
    public void setProduct(Product product) {
    	this.product = product;
    }
    
    // Retourne le prix total de la consommation
	public int getCost() {	
		return product.getPrice()*qt;
	}

	public GuestStay getStay() {
		return stay;
	}

	public void setStay(GuestStay stay) {
		this.stay = stay;
	}


	public Product getProduct() {
		return product;
	}


	public int getQuantity() {
		
		return qt;
	}


	public String getName() {
		
		return product.name;
	}
    
    

    
 

}