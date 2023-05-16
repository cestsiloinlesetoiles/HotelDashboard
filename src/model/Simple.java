package model;
import java.util.*;

public class Simple extends Room {
	// Attribut specifique à chaque piece
    public static int spec_price = 60; 
    public static int spec_bed = 1;

  
    public Simple(int num, int floor) {
     super(num,floor);
     this.setPrice(spec_price);
     this.setBeds(spec_bed);
    }

  
    public int getCost() {
        return spec_price;
    }

  

    public int getNbrofbeds() {
        return spec_bed;
    }
    

    
    public void setNbrOfbeds(int nbeds) {
        Simple.spec_bed = nbeds;
    }
    
    public void setCostroom(int nprice) {
        Simple.spec_price = nprice;
    }

    // Retourne le type de la piece
    @Override
	public String getType() {
		
		return "Simple";
	}

    
}