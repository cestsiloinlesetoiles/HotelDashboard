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
    public Date date_debut;

    /**
     * 
     */
    public Date date_fin;

    /**
     * 
     */
    public Clients client;

    /**
     * 
     */
    public Sejour sejour;

    /**
     * 
     */
    public Hotel hotel;

    /**
     * 
     */
    public Vector<Chambre> listchambre;

}