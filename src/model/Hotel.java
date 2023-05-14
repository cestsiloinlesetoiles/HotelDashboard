package model;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

public class Hotel extends Observable {

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
	
	public void setAddr(String addr) {
		this.address = addr;
		
		setChanged();
        notifyObservers();
	}
	
	
	public void setName(String name) {
		this.name = name;
		
		setChanged();
        notifyObservers();
	}
	
	public void addCustomer(Customer customer) {
		customer.setHotel(this);
		customers.add((customer));
	}
	
	public void removeCustomer(Customer customer) {
		customers.remove(customer);
	}
	
	
	public void removeOptionFromRooms(String optionName) {
	    for (Room room : rooms) {
	        
	    	if(room instanceof LuxuryRoom) {
	    	 LuxuryRoom romml = (LuxuryRoom) room;
	    	 	romml.removeOptions(optionName);
	    	}
	    	else if(room instanceof PresidentialSuite){
	    		PresidentialSuite rommp = (PresidentialSuite) room;
	    	 	rommp.removeOptions(optionName);
	    	}
	    	else {
	    		continue;
	    	}
	    	
	    }
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
				dRoom.setHotel(this);
				rooms.add(dRoom);
				break;
			case LUXURY:
				LuxuryRoom lRoom = new LuxuryRoom(num, floor);
				lRoom.setHotel(this);
				rooms.add(lRoom);
				break;
			case PRESIDENTIAL:
				PresidentialSuite pRoom = new PresidentialSuite(num, floor);
				pRoom.setHotel(this);
				rooms.add(pRoom);
				break;
			default:
				Simple sRoom = new Simple(num, floor);
				sRoom.setHotel(this);
				rooms.add(sRoom);
				break;
			}
			setChanged();
	        notifyObservers();
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
		
		setChanged();
        notifyObservers();
	}

	
	public void removeRoom(int floor, int num) {
	    for (int i = 0; i < rooms.size(); i++) {
	        if (rooms.get(i).getFloor() == floor && rooms.get(i).getNum() == num) {
	            rooms.remove(i);

	            setChanged();
	            notifyObservers();

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
	
	
	

	public void addReservation(Reservation reservation) {
		
		if(reservation.room != null) {reservation.room.reservations.add(reservation);};
		
		if(reservation.getCustomer() != null){reservation.getCustomer().getListrsv_current_customer().add(reservation);}
		
		reservation.setHotel(this);
	    reservations.add(reservation);
	}

	public void removeReservation(Reservation reservation) {
		if(reservation.room != null) {
		reservation.room.reservations.remove(reservation);};
		if(reservation.getCustomer() != null){
		reservation.getCustomer().getListrsv_current_customer().remove(reservation);};
		reservations.remove(reservation);
	}

	public Reservation getReservationById(int id) {
	    for (Reservation reservation : reservations) {
	        if (reservation.getId() == id) {
	            return reservation;
	        }
	    }
	    return null;
	}

	public GuestStay getGuestStayById(int id) {
	    for (GuestStay guestStay : guestStays) {
	        if (guestStay.getReservation().getId() == id) {
	            return guestStay;
	        }
	    }
	    return null;
	}
	
		


	
	
}
