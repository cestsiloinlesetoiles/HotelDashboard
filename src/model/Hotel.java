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



}