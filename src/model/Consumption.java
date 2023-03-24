package model;
import java.util.*;


public class Consumption {
    
	public int qt;
    public product product;
    // sejour = guestStay
    public GuestStay stay;
    
    //Default constructor
    public Consumption(product product, int qt ,GuestStay guestStay) {
    	this.product = product;
        this.stay = guestStay;
    }
// AJOUTE CONSO DANS LIST CONSO DE GUESTSTAY
    
 

}