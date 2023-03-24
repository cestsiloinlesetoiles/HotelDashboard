package model;
import java.util.*;


public class Room {

    public int num;
    public int floor;
    public int price;
    public int beds;
    public Reservation reservation;
    public Hotel hotel;
    
    public Room(int num, int floor) {
    	this.num = num;
    	this.floor = floor;
    }


}