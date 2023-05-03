package controller.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Customer;
import model.Hotel;
import view.CustomerP;

public class DelCustomerController implements ActionListener {
	JTable TableCustomerJTable;
	DefaultTableModel TableModelCustomer;
	Hotel hotel; 
	CustomerP mainPanel;
	
	public  DelCustomerController(JTable tableJTable,DefaultTableModel tableModel,Hotel hotel, CustomerP mainPanel) {
		this.TableCustomerJTable = tableJTable;
		this.TableModelCustomer =  tableModel;
		this.hotel = hotel;
		this.mainPanel = mainPanel;
	}
	
	public void actionPerformed(ActionEvent event) {
		
		int result = JOptionPane.showConfirmDialog(mainPanel, "Êtes-vous sûr de vouloir supprimer ce client ?", "Confirmation", JOptionPane.YES_NO_OPTION);
		
		if(result == 0) {
			 	
				int selectedRow = TableCustomerJTable.getSelectedRow();
			 
			  	String lastName = (String) TableCustomerJTable.getValueAt(selectedRow, 0);
			    String firstName = (String) TableCustomerJTable.getValueAt(selectedRow, 1);
			  
			    
			    
			    hotel.removeCustomer(hotel.CheckIn(firstName, lastName)); 
			 
			 
			    TableModelCustomer.removeRow(selectedRow);
			    
			 
		}
	}
}
