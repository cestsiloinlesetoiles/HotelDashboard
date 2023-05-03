package view.Custompanel;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Customer.DelCustomerController;
import controller.Customer.EditingCustomerController;
import model.Hotel;
import view.CustomerP;


public class ActionMenuP extends JPanel {
	
	JButton btnEditor;
	JButton btnDelete;
	JButton btnConsult;
	JTable TableCustomerJTable;
	DefaultTableModel TableModelCustomer;
	Hotel hotel; 
	
	
	public ActionMenuP(JTable tableJTable,DefaultTableModel tableModel,Hotel hotel,CustomerP mainPanel) {
		this.TableCustomerJTable = tableJTable;
		this.TableModelCustomer =  tableModel;
		this.hotel = hotel;
		
	    DelCustomerController delC = new DelCustomerController(tableJTable, tableModel, hotel,mainPanel);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		btnEditor = new JButton("Editer");
		btnEditor.setEnabled(false);
		EditingCustomerController edit = new EditingCustomerController(tableJTable, tableModel, hotel, mainPanel);
		btnEditor.addActionListener(edit);
		add(btnEditor);
		
		btnDelete = new JButton("Supprimer");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(delC);
		add(btnDelete);

		btnConsult = new JButton("Consulter");
		btnConsult.setEnabled(false);
		add(btnConsult);
	}

	public void enableButtons(boolean isEnabled) {
		btnEditor.setEnabled(isEnabled);
		btnDelete.setEnabled(isEnabled);
	}
	
	public void enablebtnConsult(boolean isEnabled) {
	btnConsult.setEnabled(isEnabled);
	}
}