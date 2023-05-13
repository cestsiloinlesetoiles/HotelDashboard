package model;
import java.util.*;


public class LuxuryRoom extends Room {
	
	private static int spec_price;
	private static int spec_bed;
	
	private Vector<Options> listoptions;
    
    public LuxuryRoom(int num, int floor) {
    	super(num,floor);
    	this.setPrice(spec_price);
        this.setBeds(spec_bed);
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
    
    public Vector<Options> getListOptions() {
    	return listoptions;
    }
    
    //Setup mes attribut de classes
    
    public void setNbrOfbeds(int nbeds) {
    	LuxuryRoom.spec_bed = nbeds;
    }
    
    public void setCostroom(int nprice) {
    	LuxuryRoom.spec_price = nprice;
    }
    
    public void addOptions(Options opt) {
    	listoptions.add(opt);
    }
    
    public void removeOptions(String name) {
        for (int i = 0; i < listoptions.size(); i++) {
            if (listoptions.get(i).getName().equals(name)) {
            	listoptions.remove(i);
                break; 
            }
        }
    }
    
    
    public boolean optionExists(String name) {
        for (int i = 0; i < listoptions.size(); i++) {
            if (listoptions.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public void setlistOptions(Vector<Options> listoptions) {
    	this.listoptions = listoptions;
    }
    
    @Override
    public String getType() {
		
		return "Luxury";
	}
}