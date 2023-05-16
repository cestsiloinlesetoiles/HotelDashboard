package controller.roomP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import view.RoomP;

public class ControllerDeletingRoomP implements ActionListener  {

	RoomP r;

	public ControllerDeletingRoomP(RoomP r){
		this.r = r;

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		// Meme principe que pour le controllerDeletingStayP sauf qu'on uttilise la methode removeRoom.
		if(b.getActionCommand() == "Room") {
			int[] selectedRows = r.JTableRoom.getSelectedRows();
			int numRows = selectedRows.length;

			if(numRows == 0) {
				JOptionPane.showMessageDialog(r, "Aucune ligne sélectionnée.", "Information", JOptionPane.INFORMATION_MESSAGE);
			} else {
				int result = JOptionPane.showConfirmDialog(r, "Êtes-vous sûr de vouloir supprimer " + numRows + " chambres ?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					for(int i = numRows - 1; i >= 0; i--) {
						int selectedRow = selectedRows[i];
						int floor = Integer.parseInt(r.JTableRoom.getValueAt(selectedRow, 0).toString());
						int num = Integer.parseInt(r.JTableRoom.getValueAt(selectedRow, 1).toString());
						 
						r.h.removeRoom(floor, num); 
						r.updateTableModel();

					}

				}

			}

		}else {
			int[] selectedRows = r.JTableOptions.getSelectedRows();
			int numRows = selectedRows.length;

			if(numRows == 0) {
				JOptionPane.showMessageDialog(r, "Aucune ligne sélectionnée.", "Information", JOptionPane.INFORMATION_MESSAGE);
			} else {
				int result = JOptionPane.showConfirmDialog(r, "Êtes-vous sûr de vouloir supprimer " + numRows + " options ?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					for(int i = numRows - 1; i >= 0; i--) {
						int selectedRow = selectedRows[i];
						String name = (String) r.JTableOptions.getValueAt(selectedRow, 0);
						r.removeOption(name);
					}

				}
			}

		} 

	}}

