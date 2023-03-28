package model;
import java.util.*;

public class Customer {
	  	
		public String name;
	    public String firstname;
	    public String email;
	    public String tel;
	    public Hotel hotel;
	   
	    public Vector <Reservation> listrsv_current_customer = new Vector<Reservation>() ;
    
	//Default constructor
    public Customer(String name, String firstname, String email, String tel) {
    	  this.name = name;
          this.firstname = firstname;
          this.email = email;
          this.tel = tel;

    }
    
    public void setHotel(Hotel hotel) {
    	 this.hotel = hotel;
    }
 }