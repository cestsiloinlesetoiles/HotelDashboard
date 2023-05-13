package model;
import java.util.*;


public class GuestStay {
    
    public int totalCost;
    public Reservation reservation;
    public Hotel hotel;
    public Vector <Consumption> listconspt = new Vector<Consumption>();;
    
 
    public GuestStay(Reservation reservation) {
    	this.reservation = reservation;
    }
    
    
    public void setHotel(Hotel hotel) {
   	 this.hotel = hotel;
    }
    
    public void setReservation(Reservation reservation) {
    	this.reservation = reservation;
    }
    
    public void calculateTotalCost() {

    	int roomCost = reservation.getRoom().getCost() ; 
    	int stayDuration = reservation.getStayDuration();
    	int reservationCost = roomCost * stayDuration;

    	int consumptionCost = 0;
    	
    	for ( Consumption cspt  : listconspt) {
    		consumptionCost+= cspt.getCost();
    	}
    	
    	totalCost = consumptionCost + reservationCost;
    }
    
   
}