package view;
import java.awt.Color;

import javax.swing.*;

import model.Hotel;
public class StayP extends JPanel{
	public StayP(Hotel hotel) {
		 this.setBackground(Color.GREEN);
		 JLabel title = new JLabel("StayP");
		 add(title);
	}
}
