package controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

// https://www.tutorialspoint.com/swing/swing_focus_listener.html
public class PhoneFieldController implements FocusListener {
	String Regex = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
	JLabel PhoneError;
	public PhoneFieldController(JLabel PhoneError) {
   
      this.PhoneError = PhoneError;
	}
	
    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField txtphone = (JTextField) e.getSource();
        if (txtphone.getText().matches(Regex)) {
            txtphone.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            PhoneError.setText("");
        } else {
            txtphone.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            PhoneError.setForeground(Color.RED);
            PhoneError.setText("Telephone invalide");
        }
    }
}
