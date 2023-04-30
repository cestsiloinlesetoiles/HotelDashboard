package view;
import java.awt.Color;

import javax.swing.*;

import model.HotelManager.Hotel;

public class ReservationP extends JPanel {
	public ReservationP(Hotel hotel) {
	this.setBackground(Color.CYAN);
	JLabel title = new JLabel("ReservationP");
	add(title);
	}
}
