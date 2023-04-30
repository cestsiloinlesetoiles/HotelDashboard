package model.HotelManager;
import java.util.*;
import java.time.*;

public class Hotel {

	public String name;
	public String address;

	public Vector<Reservation> reservations = new Vector<Reservation>();
	public Vector<Room> rooms = new Vector<Room>();
	public Vector<Customer> customers = new Vector<Customer>();
	public Vector<GuestStay> guestStays = new Vector<GuestStay>();
	public Vector<Options> options = new Vector<Options>(); 
	
	public Hotel(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void removeCustomer(Customer customer) {
		customers.remove(customer);
	}

	public Customer CheckIn(String firstName, String lastName) {
		for (Customer customer : customers) {
			if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
				return customer;
			}
		}
		return null;
	}

	

	public boolean isRoomExist(int floor, int num) {
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getFloor() == floor & rooms.get(i).getNum() == num) {
				return true;
			}
		}   
		return false;
	}

	public void basicAddRoom(Room room) {
		if (!(isRoomExist(room.getFloor(), room.getNum()))) {
			rooms.add(room);
		}
	}

	public void addRoom(int floor, int num, Roomtype roomType, int mode) {
		if (!(isRoomExist(floor, num))) {
			switch (roomType) {
			case DOUBLE:
				Double dRoom = new Double(floor, num);
				rooms.add(dRoom);
				break;
			case LUXURY:
				LuxaryRoom lRoom = new LuxaryRoom(floor, num);
				rooms.add(lRoom);
				break;
			case PRESIDENTIAL:
				LuxaryRoom pRoom = new LuxaryRoom(floor, num);
				rooms.add(pRoom);
				break;
			default:
				Simple sRoom = new Simple(floor, num);
				rooms.add(sRoom);
				break;
			}
		}
	}

	public void modifyRoom(int floor, int num) {

	}

	public void removeReservation(Date startDate, Date endDate, Customer customer) {

	}
}
