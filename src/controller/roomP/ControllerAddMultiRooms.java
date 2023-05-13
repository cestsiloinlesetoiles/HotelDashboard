package controller.roomP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Roomtype;
import view.RoomP;
import view.custom.MultipleAddingDialog;

public class ControllerAddMultiRooms implements ActionListener {
    public RoomP r; 
    public MultipleAddingDialog d;
    
    public ControllerAddMultiRooms(RoomP r, MultipleAddingDialog d) {
      this.r = r;
      this.d = d;
    }

    public void actionPerformed(ActionEvent e) {
    	JButton b = (JButton)e.getSource();
    	
    	
    	
    	
    	
    	if(b.getText().equals("Enregistrer")) {
        if (!d.txtNumberOfRooms.getText().isEmpty() && !d.txtStartingFloor.getText().isEmpty() && !d.txtStartingRoomNumber.getText().isEmpty()&& !d.txtCapacityPerFloor.getText().isEmpty()) {
            int numberOfRooms = Integer.parseInt(d.txtNumberOfRooms.getText());
            int startingFloor = Integer.parseInt(d.txtStartingFloor.getText());
            int startingRoomNumber = Integer.parseInt(d.txtStartingRoomNumber.getText());
            String roomTypeString = d.cbRoomType.getSelectedItem().toString();
            int capacityPerFloor = Integer.parseInt(d.txtCapacityPerFloor.getText());

            switch (roomTypeString) {
                case "Simple":
                	r.h.addRooms(numberOfRooms, Roomtype.SIMPLE, startingFloor, startingRoomNumber, capacityPerFloor);
                    break;
                case "Double":
                    r.h.addRooms(numberOfRooms, Roomtype.DOUBLE, startingFloor, startingRoomNumber, capacityPerFloor);
                    break;
                case "Luxury":
                	r.h.addRooms(numberOfRooms, Roomtype.LUXURY, startingFloor, startingRoomNumber, capacityPerFloor);
                    break;
                case "Presidential":
                	r.h.addRooms(numberOfRooms, Roomtype.PRESIDENTIAL, startingFloor, startingRoomNumber, capacityPerFloor);
                    break;
                default:
                    break;
            }
            r.updateTableModel();
            d.dispose();
        }
    }
    	else {
    		d.dispose();
    	}
    
    }
}
