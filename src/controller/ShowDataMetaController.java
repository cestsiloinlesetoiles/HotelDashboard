package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Hotel;

public class ShowDataMetaController implements ActionListener {
	Hotel h ; 
	
	public ShowDataMetaController(Hotel h) {
		this.h = h;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		h.ShowDataConsole();
	}

	
}
