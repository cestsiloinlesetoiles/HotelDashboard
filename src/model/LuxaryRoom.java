package model;
import java.util.*;


public class LuxaryRoom extends Room {
	
	private static int spec_price;
	private static int spec_bed;
	
	public Vector<Options> listoptions;
    
    public LuxaryRoom(int num, int floor) {
    	super(num,floor);
    	this.price = spec_price;
        this.beds = spec_bed;
        // list vide d'options
        this.listoptions = new Vector<>();
    }
    

    @Override
    public int getCost() {

    	int OptionsCost = 0;
    	// type element : collection
    	
    	for ( Options opts  : listoptions) {
    		OptionsCost+= opts.getCost();
    	} 
        int totalCostRoom = spec_price +  OptionsCost;
    	return totalCostRoom ;
    }

  

    public int getNbrofbeds() {
        return spec_bed;
    }
    
    //Setup mes attribut de classes
    
    public void setNbrOfbeds(int nbeds) {
    	LuxaryRoom.spec_bed = nbeds;
    }
    
    public void setCostroom(int nprice) {
    	LuxaryRoom.spec_price = nprice;
    }
    

}