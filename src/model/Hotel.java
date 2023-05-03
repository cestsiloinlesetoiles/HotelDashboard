package model;
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
			if (customer.getFirstName().equalsIgnoreCase(firstName) && customer.getLastName().equalsIgnoreCase(lastName)) {
				return customer;
			}
		}
		return null;
	}

	


	

	public void basicAddRoom(Room room) {
		if (!(isRoomExist(room.getFloor(), room.getNum()))) {
			rooms.add(room);
		}
	}

	public void addRoom(int floor, int num, Roomtype roomType) {
		if (!(isRoomExist(floor, num))) {
			switch (roomType) {
			case DOUBLE:
				DoubleRoom dRoom = new DoubleRoom(num, floor);
				rooms.add(dRoom);
				break;
			case LUXURY:
				LuxaryRoom lRoom = new LuxaryRoom(num, floor);
				rooms.add(lRoom);
				break;
			case PRESIDENTIAL:
				LuxaryRoom pRoom = new LuxaryRoom(num, floor);
				rooms.add(pRoom);
				break;
			default:
				Simple sRoom = new Simple(num, floor);
				rooms.add(sRoom);
				break;
			}
		}
	}
	
	
	
	public void addRooms(int numberOfRooms, Roomtype roomType, int floor, int currentRoomNumber, int floorCapacity ) {
	    	
		while (numberOfRooms > 0) {
	        if(floorCapacity < currentRoomNumber  ) {
	        	floor++;
	        	currentRoomNumber = 1;
	        }
			if (!isRoomExist(floor, currentRoomNumber)) {
	            addRoom(floor, currentRoomNumber, roomType);
	            numberOfRooms--;
	        }
	        currentRoomNumber++;
	    }
	}

	
	public void removeRoom(int floor, int num) {
	    for (int i = 0; i < rooms.size(); i++) {
	        if (rooms.get(i).getFloor() == floor && rooms.get(i).getNum() == num) {
	            rooms.remove(i);
	            return;
	        }
	    }
	}

	
	public boolean isRoomExist(int floor, int num) {
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getFloor() == floor & rooms.get(i).getNum() == num) {
				return true;
			}
		}   
		return false;
	}
	
	public Room getRoom(int floor, int num) {
	    for (Room room : rooms) {
	        if (room.getFloor() == floor && room.getNum() == num) {
	            return room;
	        }
	    }
	    return null;
	}
	
	
	
	

	
	
	
	
	
	public void removeReservation(Date startDate, Date endDate, Customer customer) {

	}
	
	
	public void ShowDataConsole() {
		
		
		System.out.println("-----CUSTOMERS LIST-----");
		for(int i =0; i<this.customers.size();i++) { 
			
			Customer c = this.customers.get(i);
			
			System.out.println("| "+i+" "+c.getLastName()+" "+c.getFirstName()+" |");
			
			
		}
		System.out.println("-----------------------");
		
		

		System.out.println("-----RoomList-----");
		for(int i =0; i<this.rooms.size();i++) { 
			
			Room r = this.rooms.get(i);
			
			System.out.println("| "+i+" Num: "+r.getNum()+" Etage: "+r.getFloor()+" |");
			
			
		}
		System.out.println("-----------------------");
	}
	
}
