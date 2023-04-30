package view;
import java.awt.Color;

import javax.swing.*;

import model.HotelManager.Hotel;
public class RoomP extends JPanel {
 public RoomP(Hotel hotel) {
	 this.setBackground(Color.MAGENTA);
	 JLabel title = new JLabel("RoomP");
	 add(title);
 }
}
