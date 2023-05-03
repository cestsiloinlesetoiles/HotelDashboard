package controller.Room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Hotel;

public class RomfieldCheckController implements FocusListener  {
	private JTextField txtFloor;
    private JTextField txtRoomNumber;
    private JLabel lblError;
    private Hotel hotel;

    public RomfieldCheckController(JTextField txtFloor, JTextField txtRoomNumber, JLabel lblError, Hotel hotel) {
        this.txtFloor = txtFloor;
        this.txtRoomNumber = txtRoomNumber;
        this.lblError = lblError;
        this.hotel = hotel;
    }

   

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		/*String regx = "\\d+";
		if() {
        int floor = Integer.parseInt(txtFloor.getText());
        int num = Integer.parseInt(txtRoomNumber.getText());
		}
        
        if(!txtFloor.getText().matches(regx)) {
        	lblError.setText("Champ incorrect");
        }
        else if(!txtRoomNumber.getText().matches(regx)) {
        	lblError.setText("Champ incorrect");
        	
        }
        if() {}
        else(hotel.isRoomExist(floor, num)) {
            lblError.setText("La pièce existe déjà");
        } else {
            lblError.setText("");
        }
	*/	
	}
}
