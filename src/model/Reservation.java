package model;
import java.util.*;
import java.time.*;

public class Reservation {

	private LocalDate date_start;
    private LocalDate date_end;
    private Customer customer;
    private GuestStay stay;
    private Hotel hotel;
    private Room room;
	
    
    
    public Reservation(LocalDate date_start, LocalDate date_end) {
    	LocalDate currentDate = LocalDate.now(); // Récupération de la date actuelle
    	
    	
    	// [CHANGEMENT] DATE TO LOCALDATE COMMING after to IsBefore
    	if(date_start.isAfter(date_end)) {
    	 // ERREUR
    	};
    	
    	if(date_start.isBefore(currentDate)) {
       	 // ERREUR
       	};
    	
    	this.date_start = date_start;
        this.date_end = date_end;
    }
    
    // CAS OU LA CHAMBRE NEST PAS ATTRIBUABLE QUE FAIRE ?
    
    public void setRoom(Room room){
    	// a Mettre dans le code principale if(room.CheckisFree(this.date_start,this.date_end)) {
    	 this.room = room;
    	}	
   
    
    public void setCustomer(Customer customer){
    	this.customer= customer;
    }
    
    public void setHotel(Hotel hotel) {
    	this.hotel=hotel;
    }
    
    
    public int getStayDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Room getRoom() {
		return room;
	}

	public LocalDate getDateStart() {
		return date_start;
	}

	public LocalDate getDateEnd() {
		return date_end;
	}
    
	
	
	
    
}

  
