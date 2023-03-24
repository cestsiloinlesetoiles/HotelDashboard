package model;
import java.util.*;

/**
 * 
 */
public class Hotel {

    /**
     * Default constructor
     */
    public Hotel() {
    }

    /**
     * 
     */
    public String name;

    /**
     * 
     */
    public String adress;

    /**
     * 
     */
    public Set<Reservation> Listrsv;

    /**
     * 
     */
    public Set<Room> listrooms;

    /**
     * 
     */
    public Set<Clients> listcustomer;

    /**
     * 
     */
    public Set<GuestStay> liststay;

}