package model;
import java.util.Vector;


public class PresidentialSuite extends Room {
	
	public static int spec_price = 500 ;
	public static int spec_bed = 2;
	
    public Vector<Options> listoptions;

    public PresidentialSuite(int num, int floor) {
    	super(num,floor);
    	this.setPrice(spec_price);
        this.setBeds(spec_bed);
        // list vide d'options
        this.listoptions = new Vector<>();
    }
    
    //Setup mes attribut de classes
    public Vector<Options> getListOptions() {
    	return listoptions;
    }
    
    
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
    
    public void addOptions(Options opt) {
    	if(optionExists(opt.name)) {
    	listoptions.add(opt);
    	}
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
		
		return "PresidentialSuite";
	}
}
