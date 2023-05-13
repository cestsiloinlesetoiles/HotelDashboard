package controller.reservationP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.ReservationP;

public class ControllerDelReservations implements ActionListener {
	ReservationP r;

	public ControllerDelReservations(ReservationP r) {
		this.r = r;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int[] selectedRows = r.JTableReserv.getSelectedRows();
		int numRows = selectedRows.length;

		if (numRows == 0) {
			JOptionPane.showMessageDialog(r, "Aucune ligne sélectionnée.", "Information", JOptionPane.INFORMATION_MESSAGE);
		} else {
			int result = JOptionPane.showConfirmDialog(r, "Êtes-vous sûr de vouloir supprimer " + numRows + " réservations ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				for (int i = numRows - 1; i >= 0; i--) {
					int selectedRow = selectedRows[i];
					int id = Integer.parseInt(r.JTableReserv.getValueAt(selectedRow, 0).toString());
					
					r.h.removeReservation(r.h.getReservationById(id));
					r.updateTableModel();
				}
			}
		}
	}
}
