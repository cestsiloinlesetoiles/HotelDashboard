package model;
import java.util.*;


public class Hotel {
    
    public String name;
    public String address;
    
    // Init list => empty by default
    public Vector<Reservation> Listrsv = new Vector<Reservation>();
    public Vector<Room> listrooms = new Vector<Room>();
    public Vector<Customer> listcustomer = new Vector<Customer>();
    public Vector<GuestStay> liststay = new Vector<GuestStay>();
    
    //Default Constructor
    public Hotel(String name, String address) {
    	this.name = name;
    	this.address = address;
    }
    
    
    
    // Verifie l'existance d'une chambre id par floor et num 
    public boolean isRoomExist(int floor, int num) {
    	for(int i=0; i<listrooms.size(); i++) {
    		if(listrooms.get(i).getFloor() == floor & listrooms.get(i).getNum() == num) {
    			 return true;
    		}
    	}	
    		
    	return false;
    }
    
    //Ajout de Chambre
    
    public void BasicAddRom(Room room) {
    	if(!(isRoomExist(room.getFloor(),room.getNum()))) {
    	listrooms.add(room);
    	}
    }
    
    //Ajout de Chambre par creation 
    public void addRoom(int floor, int num, Roomtype Roomtype, int mode) {
    	
    	// Check integrity 
    	if(!(isRoomExist(floor,num))){
    		//Créer une nouvelle chambre
    	switch(Roomtype) {
        case DOUBLE:
            // Code pour le type "double"
        	Double Droom = new Double(floor,num);
        	listrooms.add(Droom);
            break;
        case LUXURY:
            // Code pour le type "luxury"
        	LuxaryRoom Lroom = new LuxaryRoom(floor,num);
        	listrooms.add(Lroom);
            break;
        case PRESIDENTIAL:
            // Code pour le type "presidential"
        	LuxaryRoom Proom = new LuxaryRoom(floor,num);
        	listrooms.add(Proom);
            break;
        default:
            // Code pour le cas par défaut (si la valeur ne correspond à aucun des cas ci-dessus)
        	Simple Sroom = new Simple(floor,num);
        	listrooms.add(Sroom);
         break;}
    	}
    }
    
    // Adding multiple 
    
    public void addByfloor(int floor, int num, Roomtype  typeRoom) {
    	
    }
   
    // Modification chambre 
    public void modRomm(int floor, int num) {
    	
    }
    
    //Annuler de reservation 
    public void  RmRes(Date date_start, Date date_end, Customer customer) {
    }
    
}