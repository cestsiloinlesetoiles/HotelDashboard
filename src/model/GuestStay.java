package model;
import java.util.*;

//sejour = guestStay à voir
public class GuestStay {
    
    public int totalCost;
    public Reservation reservation;
    public Hotel hotel;
    public Vector <Consumption> listconspt = new Vector<Consumption>();;
    
    // Total init a zero => 
    public GuestStay() {
    }
    
    
    public void setHotel(Hotel hotel) {
   	 this.hotel = hotel;
    }
    
    public void setReservation(Reservation reservation) {
    	this.reservation = reservation;
    }
    
    public void calculateTotalCost() {
    	// calculer le coût de la réservation
    	
    	int roomCost = reservation.getRoom().getCost() ; 
    	int stayDuration = reservation.getStayDuration();
    	int reservationCost = roomCost * stayDuration;
    	
    	
    	// calculer le coût des consommations
    	int consumptionCost = 0;
    	// type element : collection
    	for ( Consumption cspt  : listconspt) {
    		consumptionCost+= cspt.getCost();
    	}
    	
    	totalCost = consumptionCost + reservationCost;
    }
    
    // Methode pour calculer totalcost par défaut = 0 car type primaire avec listconst et res.room avec les date de réserv
}