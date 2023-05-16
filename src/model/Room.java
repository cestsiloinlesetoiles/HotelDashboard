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
    

	// Vérifier si la chambre est libre pour une période donnée
    public boolean CheckisFree(LocalDate start, LocalDate end){
    	// voir si la réservation chevauche la période donnée pour chaque réservation
		for (Reservation res : reservations) {
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


	// Juste pour les tests
	public String getType() {
		return "ERROR";
	}

	// Ajouter une réservation à la liste des réservations de la chambre pour check non disponibilité
	public void addReservation(Reservation reservation) {
	    this.reservations.add(reservation);
	}
	


}