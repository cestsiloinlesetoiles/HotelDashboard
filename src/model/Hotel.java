package model;
import java.util.*;


public class Hotel {
    
    public String name;
    public String address;
    public Vector<Reservation> Listrsv;
    public Vector<Room> listrooms;
    public Vector<Customer> listcustomer;
    public Vector<GuestStay> liststay;
    
    //Default Constructor
    public Hotel(String name, String address) {
    	this.name = name;
    	this.address = address;
        this.Listrsv = new Vector<Reservation>();
        this.listrooms = new Vector<Room>();
        this.listcustomer = new Vector<Customer>();
        this.liststay = new Vector<GuestStay>();

    }
    
    //Ajout de Chambre	
    public void addRoom(int floor, int num, String typeRoom) {  	
    }
    
    //Ajout de Reservation
    public void  MkRes(Date date_start, Date date_end, Customer customer) {
    }
    
    //Annuler de reservation 
    public void  RmRes(Date date_start, Date date_end, Customer customer) {
    }
  


}