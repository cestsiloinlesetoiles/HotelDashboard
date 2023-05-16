package model;
import java.util.*;


public class LuxuryRoom extends Room {
	
	public static int spec_price = 200;
	public static int spec_bed = 1;
	
	private Vector<Options> listoptions;
    
    public LuxuryRoom(int num, int floor) {
    	super(num,floor);
    	this.setPrice(spec_price);
        this.setBeds(spec_bed);
        this.listoptions = new Vector<>();
    }
    
    // CALCUL DU COUT DE LA CHAMBRE EN FONCTION DES OPTIONS ET DU PRIX DE BASE
    @Override
    public int getCost() {

    	int OptionsCost = 0;
    	
    	for ( Options opts  : listoptions) {
    		OptionsCost+= opts.getCost();
    		System.out.println(" Total Opt : " + OptionsCost);
    	} 
    	
        int totalCostRoom = spec_price +  OptionsCost;
        System.out.println("RoomCost " + totalCostRoom);
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
    
    // remove option de la liste des options
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