package controller.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import model.Customer;
import model.Hotel;
import view.CustomerP;
import view.Dialogs.EditCustomerDialog;

public class EditingCustomerController implements ActionListener {
	JTable TableCustomerJTable;
	DefaultTableModel TableModelCustomer;
	Hotel hotel; 
	CustomerP mainPanel;
	
	public  EditingCustomerController(JTable tableJTable,DefaultTableModel tableModel,Hotel hotel, CustomerP mainPanel) {
		this.TableCustomerJTable = tableJTable;
		this.TableModelCustomer =  tableModel;
		this.hotel = hotel;
		this.mainPanel = mainPanel;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int selectedRow = TableCustomerJTable.getSelectedRow();
		 
	  	String lastName = (String) TableCustomerJTable.getValueAt(selectedRow, 0);
	    String firstName = (String) TableCustomerJTable.getValueAt(selectedRow, 1);
	    Customer Currentcustomer = hotel.CheckIn(firstName, lastName);
	    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
	    EditCustomerDialog editDialog = new EditCustomerDialog(parentFrame,Currentcustomer,selectedRow,TableModelCustomer,hotel);
	    editDialog.setVisible(true);
	    
		
	}

}
