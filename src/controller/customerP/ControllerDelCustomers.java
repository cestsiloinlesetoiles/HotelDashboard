package controller.customerP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.CustomerP;

public class ControllerDelCustomers implements ActionListener  {
	CustomerP c;
	
	public ControllerDelCustomers(CustomerP c){
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int[] selectedRows = c.JTableCustomer.getSelectedRows();
		int numRows = selectedRows.length;
		// meme principe que pour les reservations etc
		if(numRows == 0) {
			JOptionPane.showMessageDialog(c, "Aucune ligne sélectionnée.", "Information", JOptionPane.INFORMATION_MESSAGE);
		} else {
			int result = JOptionPane.showConfirmDialog(c, "Êtes-vous sûr de vouloir supprimer " + numRows + " clients ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				for(int i = numRows - 1; i >= 0; i--) {
					int selectedRow = selectedRows[i];
					String lastName = (String) c.JTableCustomer.getValueAt(selectedRow, 0);
					String firstName = (String) c.JTableCustomer.getValueAt(selectedRow, 1);
					c.h.removeCustomer(c.h.CheckIn(firstName, lastName)); 
					c.updateTableModel();
				}

			}

		}
		
	}

}
