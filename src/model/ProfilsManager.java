package model;

import java.util.Vector;

import model.HotelManager.Hotel;

public class ProfilsManager {
	Vector<Profil> ListProfils = new Vector<Profil>();
	public ProfilsManager() {

	}

	public Profil MatchingUser(String n , String p){
		for(int i = 0; i < ListProfils.size(); i++) {
			if(ListProfils.get(i).MatchAccount( n ,  p)) {
				return ListProfils.get(i);

			}
		}
		return null;
	}
	
	
	public boolean CreateNewUser(String n , String p, Hotel hotel) {
		if(MatchingUser(n , p ) == null) {
			int numUsers = ListProfils.size();
			Profil user = new Profil(n,p,hotel);
			ListProfils.add(user);
			return true;	
		}
		return false;
	}
	
	
	
}
