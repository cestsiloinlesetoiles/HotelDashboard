package controller.Room;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Hotel;
import model.Roomtype;
import view.RoomP;

public class AddingRoomController implements ActionListener {
	private JTextField txtFloor;
	private JTextField txtRoomNumber;
	private JComboBox<String> cbRoomType;
	private Hotel hotel;
	private RoomP mainPanel;
	
	JLabel lblCapacityPerFloor;
	public AddingRoomController(JTextField txtFloor, JTextField txtRoomNumber, JComboBox<String> cbRoomType, Hotel hotel, RoomP mainPanel) {
		this.txtFloor = txtFloor;
		this.txtRoomNumber = txtRoomNumber;
		this.cbRoomType = cbRoomType;
		this.hotel = hotel;
		this.mainPanel = mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(!txtFloor.getText().isEmpty()&&!txtRoomNumber.getText().isEmpty()) {
			int floor = Integer.parseInt(txtFloor.getText());
			int roomNumber = Integer.parseInt(txtRoomNumber.getText());
			String roomTypeStr = (String) cbRoomType.getSelectedItem();
			Roomtype roomType = Roomtype.SIMPLE;
			switch (roomTypeStr) {
			case "Double":
				roomType = Roomtype.DOUBLE;
				break;
			case "Luxury":
				roomType = Roomtype.LUXURY;
				break;
			case "Presidential":
				roomType = Roomtype.PRESIDENTIAL;
				break;
			default:
				break;
			}	
			hotel.addRoom(floor, roomNumber, roomType);
			mainPanel.updateTableModel(hotel);
		}

	}

}
