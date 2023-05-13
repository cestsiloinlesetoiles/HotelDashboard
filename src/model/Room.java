package model;
import java.util.*;
import java.time.*;

public class Room {

    public int num;
    public int floor;
    public int price;
    public int beds;
    public Vector<Reservation> reservations = new Vector<Reservation>() ;
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
    
    public boolean CheckisFree(LocalDate start, LocalDate end){
    	// Parcourir toutes les réservations de la chambre
    	// Vérifier simplement si end n'est pas inférieur à start et si end n'est pas supérieur à start
    	for (Reservation res : reservations) {
    		  // Vérifier si la réservation chevauche
    		 if (!(end.isBefore(res.getDateStart()) || start.isAfter(res.getDateEnd()))) return false;
    	 }
    	return true;
    }



	public int getCost() {
		return 0;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getBeds() {
		return beds;
	}



	public void setBeds(int beds) {
		this.beds = beds;
	}
	
	public void setFloor(int floor) {
		this.floor = floor; 
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;	
	}



	public String getType() {
		return "ERROR";
	}

	
	public void addReservation(Reservation reservation) {
	    this.reservations.add(reservation);
	}
	


}