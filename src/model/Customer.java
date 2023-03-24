package model;
import java.util.*;

public class Customer {
	  	
		public String name;
	    public String firstname;
	    public String email;
	    public String tel;
	    public Vector<Reservation> listrsv;
	    public Hotel hotel;
	    
    //Default constructor
     
    public Customer(String name, String firstname, String email, String tel, Hotel hotel) {
    	  this.name = name;
          this.firstname = firstname;
          this.email = email;
          this.tel = tel;
          this.hotel = hotel;
          this.listrsv = hotel.Listrsv;
    }

}