package model;
import java.util.*;

public class Simple extends Room {
	private static int spec_price; 
    private static int spec_bed;
    
    
    public Simple(int num, int floor) {
     super(num,floor);
     this.price = spec_price;
     this.beds = spec_bed;
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
    
    
}