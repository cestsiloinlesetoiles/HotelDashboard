package controller.Room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import model.Hotel;
import view.RoomP;

public class DeleteRoomController implements ActionListener {

    private Hotel hotel;
    private JTable tableRoom;
    private RoomP roomP;

    public DeleteRoomController(Hotel hotel, JTable tableRoom, RoomP roomP) {
        this.hotel = hotel;
        this.tableRoom = tableRoom;
        this.roomP = roomP;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = tableRoom.getSelectedRow();
        
        if (selectedRow != -1) {
        	System.out.println("****DeleteRoomCommand***");
        	int roomNum = Integer.parseInt(tableRoom.getValueAt(selectedRow, 1).toString());
            int floor = Integer.parseInt(tableRoom.getValueAt(selectedRow, 0).toString());
            hotel.removeRoom(floor, roomNum);;
            roomP.updateTableModel(hotel);
        }
    }
}
