package model;
import java.util.*;

public class Customer {
	
	public String LastName;
	public String Firstname;
	public String Email;
	public String Phone;
	public Hotel Hotel;
	private static int IdCount = 1;
	private int CurrentId; // l'ID de ce client
	private Vector <Reservation> listrsv_current_customer = new Vector<Reservation>() ;


	public Customer(String name, String firstname, String email, String phone) {
		this.setLastName(name);
		this.setFirstname(firstname);
		this.setEmail(email);
		this.setPhone(phone);
		// On incrémente l'ID de 1 à chaque fois qu'on crée un nouveau client
		CurrentId = IdCount;
	}

	public void setHotel(Hotel hotel) {
		this.Hotel = hotel;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String name) {
		this.LastName = name;
	}

	public String getFirstName() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		this.Firstname = firstname;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getPhoneNumber() {
		return Phone;
	}

	public void setPhone(String tel) {
		this.Phone = tel;
	}

	public Vector <Reservation> getListrsv_current_customer() {
		return listrsv_current_customer;
	}

	public void setListrsv_current_customer(Vector <Reservation> listrsv_current_customer) {
		this.listrsv_current_customer = listrsv_current_customer;
	}
	
	public void addReservation(Reservation reservation) {
	    this.listrsv_current_customer.add(reservation);
	}
	
}