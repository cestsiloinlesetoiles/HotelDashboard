package controller.Room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Hotel;
import model.Roomtype;
import view.RoomP;
import view.Dialogs.MultipleAddingDialog;

public class AddingMultipleRoomsController implements ActionListener {
    private JTextField txtNumberOfRooms;
    private JTextField txtStartingFloor;
    private JTextField txtStartingRoomNumber;
    private JComboBox<String> cbRoomType;
    private Hotel hotel;
    private JTextField txtCapacityPerFloor;
    private MultipleAddingDialog dialog;
    private RoomP r; 
    
    public AddingMultipleRoomsController(JTextField txtNumberOfRooms, JTextField txtStartingFloor, JTextField txtStartingRoomNumber, JComboBox<String> cbRoomType, Hotel hotel, JTextField txtCapacityPerFloor, RoomP r,MultipleAddingDialog dialog) {
        this.txtNumberOfRooms = txtNumberOfRooms;
        this.txtStartingFloor = txtStartingFloor;
        this.txtStartingRoomNumber = txtStartingRoomNumber;
        this.cbRoomType = cbRoomType;
        this.hotel = hotel;
        this.txtCapacityPerFloor = txtCapacityPerFloor;
        this.r = r;
        this.dialog = dialog;
    }

    public void actionPerformed(ActionEvent e) {
    	JButton b = (JButton)e.getSource();
    	
    	if(b.getText().equals("Enregistrer")) {
        if (!txtNumberOfRooms.getText().isEmpty() && !txtStartingFloor.getText().isEmpty() && !txtStartingRoomNumber.getText().isEmpty()&& !txtCapacityPerFloor.getText().isEmpty()) {
            int numberOfRooms = Integer.parseInt(txtNumberOfRooms.getText());
            int startingFloor = Integer.parseInt(txtStartingFloor.getText());
            int startingRoomNumber = Integer.parseInt(txtStartingRoomNumber.getText());
            String roomTypeString = cbRoomType.getSelectedItem().toString();
            int capacityPerFloor = Integer.parseInt(txtCapacityPerFloor.getText());

            switch (roomTypeString) {
                case "Simple":
                    hotel.addRooms(numberOfRooms, Roomtype.SIMPLE, startingFloor, startingRoomNumber, capacityPerFloor);
                    break;
                case "Double":
                    hotel.addRooms(numberOfRooms, Roomtype.DOUBLE, startingFloor, startingRoomNumber, capacityPerFloor);
                    break;
                case "Luxury":
                    hotel.addRooms(numberOfRooms, Roomtype.LUXURY, startingFloor, startingRoomNumber, capacityPerFloor);
                    break;
                case "Presidential":
                    hotel.addRooms(numberOfRooms, Roomtype.PRESIDENTIAL, startingFloor, startingRoomNumber, capacityPerFloor);
                    break;
                default:
                    break;
            }
            r.updateTableModel(hotel);
            dialog.dispose();
        }
    }
    	else {
    		dialog.dispose();
    	}
    
    }
}
