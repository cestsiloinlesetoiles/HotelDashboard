package model;
import java.util.*;


public class GuestStay {
    
    public int totalCost = 0;
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
    
    // CALCUL DU COUT TOTAL DU SEJOUR
    public void calculateTotalCost() {
    	int reservationCost = 0;

        // Si la réservation n'a pas de chambre, le coût de la réservation est de 0
    	if(reservation.getRoom()!=null) {
            
    	int roomCost = reservation.getRoom().getCost() ; 
    	
    	int stayDuration = reservation.getStayDuration();
        // Calcul du coût de la réservation en fonction de la durée du séjour
    	reservationCost = roomCost * stayDuration;
    
    	}
    	int consumptionCost = 0;
    	
    	for ( Consumption cspt  : listconspt) {
    		consumptionCost+= cspt.getCost();
    	}
    	// Calcul du coût total du séjour AVEC la consommation et la réservation 
    	totalCost = consumptionCost + reservationCost;
    	
    }
    
    // RECUPERER UNE CONSO PAR SON NOM
    public Consumption getConsumptionByName(String name) {
        for (Consumption cspt : listconspt) {
            if (cspt.getName().equals(name)) {
                return cspt;
            }
        }
        return null; 
    }


	public Reservation getReservation() {
		
		return reservation;
	}
}   
    
   
