package model;
import java.util.*;


public class PresidentialSuite extends Room {
	
	private static int spec_price;
	private static int spec_bed;
	
    public Vector<Options> listoptions;

    public PresidentialSuite(int num, int floor) {
    	super(num,floor);
    	this.setPrice(spec_price);
        this.setBeds(spec_bed);
        // list vide d'options
        this.listoptions = new Vector<>();
    }
    
    //Setup mes attribut de classes
    
    
    @Override
    public int getCost() {
    
    	int OptionsCost = 0;

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
    	PresidentialSuite.spec_bed = nbeds;
    }
    
    public void setCostroom(int nprice) {
    	PresidentialSuite.spec_price = nprice;
    }
    
}