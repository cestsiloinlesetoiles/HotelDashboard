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
	
	

	public void showDataConsole() {
	    System.out.println("-----Customers List-----");
	    System.out.println("| ID | Last Name | First Name | Reservations |");
	    System.out.println("----------------------------------------------");
	    for (int i = 0; i < customers.size(); i++) {
	        Customer customer = customers.get(i);
	        List<Reservation> customerReservations = customer.getListrsv_current_customer();
	        StringBuilder reservations = new StringBuilder();
	        for (Reservation reservation : customerReservations) {
	            reservations.append(reservation.getId()).append(", ");
	        }
	        if (reservations.length() > 0) {
	            reservations.setLength(reservations.length() - 2); // Remove the last comma and space
	        }
	        System.out.printf("| %-2d | %-9s | %-10s | %-12s |%n", i, customer.getLastName(), customer.getFirstName(),
	                reservations.toString());
	    }
	    System.out.println("----------------------------------------------");

	    System.out.println("\n-----Rooms List-----");
	    System.out.println("| Room Number | Floor | Type     | Reservations |");
	    System.out.println("------------------------------------------------");
	    for (int i = 0; i < rooms.size(); i++) {
	        Room room = rooms.get(i);
	        String roomType = "";
	        if (room instanceof DoubleRoom) {
	            roomType = "Double";
	        } else if (room instanceof LuxuryRoom) {
	            roomType = "Luxury";
	        } else if (room instanceof PresidentialSuite) {
	            roomType = "Presidential";
	        } else if (room instanceof Simple) {
	            roomType = "Simple";
	        }
	        List<Reservation> roomReservations = room.reservations;
	        StringBuilder reservations = new StringBuilder();
	        for (Reservation reservation : roomReservations) {
	            reservations.append(reservation.getId()).append(", ");
	        }
	        if (reservations.length() > 0) {
	            reservations.setLength(reservations.length() - 2); // Remove the last comma and space
	        }
	        System.out.printf("| %-11d | %-5d | %-8s | %-12s |%n", room.getNum(), room.getFloor(), roomType,
	                reservations.toString());
	    }
	    System.out.println("------------------------------------------------");

	    System.out.println("\n-----Reservations List-----");
	    System.out.println("| ID | Room Number | Client Name | Check-in Date | Check-out Date |");
	    System.out.println("-----------------------------------------------------------------");
	    for (Reservation reservation : reservations) {
	        int roomId = (reservation.getRoom() != null) ? reservation.getRoom().getNum() : -1;
	        String clientName = (reservation.getCustomer() != null) ? reservation.getCustomer().getLastName() + " " +
	                reservation.getCustomer().getFirstName() : "N/A";
	        System.out.printf("| %-2d | %-11d | %-11s | %-13s | %-14s |%n", reservation.getId(), roomId, clientName,
	                reservation.getDateStart().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
	                reservation.getDateEnd().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	    }
	    System.out.println("-----------------------------------------------------------------");
	}

	
	
	
}
