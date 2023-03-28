package model;
import java.util.*;


public class Room {

    public int num;
    public int floor;
    public int price;
    public int beds;
    // LIST REV ASSIGNER A UNE ROOM
    public Vector<Reservation> reservation = new Vector<Reservation>() ;
    public Hotel hotel;
    
    public Room(int num, int floor) {
    	this.num = num;
    	this.floor = floor;
    }
    
   
    
    // Pour room geters
    public int getNum() {
        return num;
    }
    
    public int getFloor() {
        return floor;
    }
    
    public boolean CheckisFree(Date start, Date end){
    	// Parcourir toutes les réservations de la chambre
    	
    	for (Reservation res : reservation) {
    		  // Vérifier si la réservation chevauche
    		
    		// Cas même date
    		if(res.getDateStart().equals(start) && res.getDateEnd().equals(end)) return false;
    		// Chevauchement sur la gauche periode
    		else if(res.getDateStart().after(end)&& res.getDateEnd().before(end)) return false;
    		// Chevauchement sur la droite periode 
    		else if(res.getDateStart().after(start)&& res.getDateEnd().before(start)) return false;
    		
    	}
    	return false;
    }



	public int getCost() {
		// 0 zero code d'erreur sur room car les vrai définie par héritage
		return 0;
	}

}