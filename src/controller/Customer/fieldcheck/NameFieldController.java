package controller.Customer.fieldcheck;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

// https://www.tutorialspoint.com/swing/swing_focus_listener.htm
public class NameFieldController implements FocusListener {
	String Regex = "([a-zA-Z',.-]+( [a-zA-Z',.-]+)*){2,30}";
	JLabel NameError;
	boolean IsLastName;
	
	public NameFieldController( JLabel NameError, boolean IsLastName) {
        this.NameError = NameError;
        this.IsLastName = IsLastName;
    }
	
    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField txtName = (JTextField) e.getSource();
        if (txtName.getText().matches(Regex)||txtName.getText().isEmpty()) {
            txtName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            NameError.setText("");
        } else {
            txtName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            NameError.setForeground(Color.RED);
            if(IsLastName)NameError.setText("Nom invalide");
            else NameError.setText("Prenom invalide");
            
        }
    }
}
