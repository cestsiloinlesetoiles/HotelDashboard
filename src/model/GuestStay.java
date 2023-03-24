package model;
import java.util.*;

//sejour = guestStay
public class GuestStay {
    
    public int totalCost;
    public Reservation reservation;
    public Hotel hotel;
    public Vector<Consumption> listconspt;
    
    //Default constructor
    public GuestStay(Reservation reservation,Hotel hotel) {
    	this.reservation = reservation;
    	this.listconspt = new Vector<Consumption>();
    	this.hotel = hotel;
    }

    // Methode pour calculer totalcost par défaut = 0 car type primaire avec listconst et hotel.room avec les date de réserv
}