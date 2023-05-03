package controller.Customer.fieldcheck;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

// https://www.tutorialspoint.com/swing/swing_focus_listener.htm
public class EmailFieldController implements FocusListener {
	String Regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
	JLabel EmailError;
	public EmailFieldController(JLabel EmailError) {
		this.EmailError = EmailError;
	}
    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField txtEmail = (JTextField) e.getSource();
        if (txtEmail.getText().matches(Regex)||txtEmail.getText().isEmpty()) {
        	 txtEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        	 EmailError.setText("");
        } else {
        	txtEmail.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        	txtEmail.setForeground(Color.RED);
            EmailError.setText("Email invalide");
        }
    }
}
