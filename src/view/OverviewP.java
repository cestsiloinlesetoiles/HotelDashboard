package view;

import java.awt.Color;

import javax.swing.*;

import model.Hotel;

public class OverviewP extends JPanel {
 public OverviewP(Hotel hotel){
	 this.setBackground(Color.BLUE);
	 JLabel title = new JLabel("OverviewP");
	 add(title);
 }
}
