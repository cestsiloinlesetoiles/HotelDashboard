package model;
import java.util.*;


public class DoubleRoom extends Room {

    public static int spec_bed = 2;
	public static int spec_price = 120;

	///Default constructor
    public DoubleRoom(int num, int floor) {
    	 super(num,floor);
         this.setPrice(spec_price);
         this.setBeds(spec_bed);
    }
    
    //Setup mes attribut de classes
   
    public int getCost() {
        return spec_price;
    }

  

    public int getNbrofbeds() {
        return spec_bed;
    }
    
    //Setup mes attribut de classes
    
    public void setNbrOfbeds(int nbeds) {
        DoubleRoom.spec_bed = nbeds;
    }
    
    public void setCostroom(int nprice) {
        DoubleRoom.spec_price = nprice;
    }
    
    @Override
    public String getType() {
		
		return "Double";
	}

}