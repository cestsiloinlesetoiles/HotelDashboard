package model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
	
	private static int count;
	private int id; 
	public LocalDate date_start;
    public LocalDate date_end;
    public Customer customer;
    public GuestStay stay;
    public Hotel hotel;
    public Room room;
	
    
    
    public Reservation(LocalDate date_start, LocalDate date_end) { 	
    	this.date_start = date_start;
        this.date_end = date_end;
        id = count;
        count++;
    }
    
   
    
    public void setRoom(Room room){
   	 this.room = room;
   }	
  
   
   public void setCustomer(Customer customer){
   	this.customer= customer;
   }
   
   public void setHotel(Hotel hotel) {
   	this.hotel=hotel;
   }
   
   
   public int getStayDuration() {
	   return (int) ChronoUnit.DAYS.between(date_start, date_end);
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

	public GuestStay getStay() {
		return stay;
	}

	public void setStay(GuestStay stay) {
		this.stay = stay;
	}

	public int getId() {
		return id;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setDateStart(LocalDate date) {
		this.date_start = date;
		
	}



	public void setDateEnd(LocalDate date) {
		 this.date_end = date;
		
	}
	
	
	
    
}

  
