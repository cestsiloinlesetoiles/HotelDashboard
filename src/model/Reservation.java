package model;
import java.util.*;


public class Reservation {

    public Date date_start;
    public Date date_end;
    public Customer customer;
    public GuestStay stay;
    public Hotel hotel;
    public Room room;
	
    // manque des truc ici verif sejour ou pas comment confirmer?;
    // CORRIGER CETTE PARTIE CONSTRUCTOR METTRE LES CONSTRUCTEUR DE BASE
    /*public Reservation(Date date_start, Date date_end, Customer customer, Hotel hotel) {
    	this.date_start = date_start;
        this.date_end = date_end;
        this.customer= customer;
        //this.listrooms = hotel.listrooms;
        //this.listrooms = new Vector<Room>;
    }*/
    
    
    
    
    // Methode
    public int getStayDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Room getRoom() {
		return room;
	}

	public Date getDateStart() {
		return date_start;
	}

	public Date getDateEnd() {
		return date_end;
	}
    
	
    
}

  
