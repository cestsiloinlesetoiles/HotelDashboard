package model;
import java.util.*;

/**
 * 
 */
public class Reservation {

    /**
     * Default constructor
     */
    public Reservation() {
    }

    /**
     * 
     */
    public Date date_start;

    /**
     * 
     */
    public Date date_end;

    /**
     * 
     */
    public Clients customer;

    /**
     * 
     */
    public GuestStay stay;

    /**
     * 
     */
    public Hotel hotel;

    /**
     * 
     */
    public Set<Room> listrooms;

}