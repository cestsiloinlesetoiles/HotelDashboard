package model;
import java.util.*;


public class Double extends Room {

    private static int spec_bed;
	private static int spec_price;

	///Default constructor
    public Double(int num, int floor) {
    	 super(num,floor);
         this.price = spec_price;
         this.beds = spec_bed;
    }
    
    //Setup mes attribut de classes
    @Override
    public int getCost() {
        return spec_price;
    }

  

    public int getNbrofbeds() {
        return spec_bed;
    }
    
    //Setup mes attribut de classes
    
    public void setNbrOfbeds(int nbeds) {
        Double.spec_bed = nbeds;
    }
    
    public void setCostroom(int nprice) {
        Double.spec_price = nprice;
    }
    

}