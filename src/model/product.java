package model;
import java.util.*;

public class product {

    public String name;
    public int price;
    // LIST NE SERT A RIEN VOIR PLUS TARD
    public Vector<Consumption> listconspt;
    
    public product(String name, int price) {
    	this.name = name;
        this.price = price;
    }

}