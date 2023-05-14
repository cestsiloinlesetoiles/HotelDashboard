package model;
import java.util.*;

public class Simple extends Room {
	public static int spec_price = 60; 
    public static int spec_bed = 1;
    //"seteur et getter de class"
    
    public Simple(int num, int floor) {
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
        Simple.spec_bed = nbeds;
    }
    
    public void setCostroom(int nprice) {
        Simple.spec_price = nprice;
    }
    @Override
	public String getType() {
		
		return "Simple";
	}

    
}