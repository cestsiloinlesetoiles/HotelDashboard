package model;

import model.HotelManager.Hotel;

public class Profil extends UserModel {
	private Hotel hotel;



	public Profil(String name, String password , Hotel hotel) {
		super(name, password);
		this.hotel = hotel;

	} 




	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	




}


